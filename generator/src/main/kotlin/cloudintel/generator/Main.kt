package cloudintel.generator

import kotlinx.cli.*
import java.io.File
import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter
import java.time.*
import kotlin.random.Random

fun main(args: Array<String>) {
    val parser = ArgParser("cur-gen")
    val provider by parser.option(ArgType.Choice(listOf("aws","gcp","azure")), shortName = "p", description = "Cloud provider").required()
    val rows     by parser.option(ArgType.Int, shortName = "n", description = "Number of rows").default(100_000)
    val outFile  by parser.option(ArgType.String, shortName = "o", description = "Output file path").default("${provider}_cur.csv")
    parser.parse(args)

    File(outFile).writer().use { writer ->
        csvWriter().open(writer) {
            writeRow( // header
                "identity_line_item_id",
                "bill_billing_period_start_date",
                "line_item_usage_account_id",
                "product_product_name",
                "line_item_usage_type",
                "pricing_public_on_demand_cost",
                "line_item_usage_start_date",
                "line_item_usage_end_date"
            )

            repeat(rows) {
                val id = "li-${Random.nextLong(1_000_000, 9_999_999)}"
                val now = LocalDate.now().minusDays(Random.nextLong(0, 30))
                val usageStart = now.atStartOfDay().plusHours(Random.nextLong(0, 23)).atOffset(ZoneOffset.UTC)
                writeRow(
                    id,
                    now.withDayOfMonth(1),
                    Random.nextLong(100_000_000_000, 999_999_999_999),
                    listOf("AmazonEC2","AmazonS3","AmazonRDS").random(),
                    listOf("BoxUsage:m6i.xlarge","TimedStorage-S3","InstanceUsage:rds.t3.medium").random(),
                    "%.3f".format(Random.nextDouble(0.01, 10.0)),
                    usageStart,
                    usageStart.plusHours(1)
                )
            }
        }
    }
    println("✅  Wrote $rows rows to $outFile for provider=$provider")
}
