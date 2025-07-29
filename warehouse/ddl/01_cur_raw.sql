/* ---------- base raw table --------------------------------------------- */
CREATE TABLE IF NOT EXISTS cur_raw
(
    identity_line_item_id          String,
    bill_billing_period_start_date Date,
    line_item_usage_account_id     String,
    product_product_name           String,
    line_item_usage_type           String,
    pricing_public_on_demand_cost  Float64,
    line_item_usage_start_date     DateTime64,
    line_item_usage_end_date       DateTime64
)
ENGINE = MergeTree
PARTITION BY toYYYYMM(bill_billing_period_start_date)
ORDER BY (line_item_usage_account_id, line_item_usage_start_date);

/* ---------- daily aggregation view ------------------------------------- */
CREATE MATERIALIZED VIEW IF NOT EXISTS daily_cost
ENGINE = SummingMergeTree
PARTITION BY toYYYYMM(bill_billing_period_start_date)
ORDER BY (line_item_usage_account_id, bill_billing_period_start_date)
AS
SELECT
    bill_billing_period_start_date,
    line_item_usage_account_id,
    sum(pricing_public_on_demand_cost) AS cost
FROM cur_raw
GROUP BY
    bill_billing_period_start_date,
    line_item_usage_account_id;
