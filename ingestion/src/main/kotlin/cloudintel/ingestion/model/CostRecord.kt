package cloudintel.ingestion.model

data class CostRecord(
    val id: String,
    val startDate: String,
    val account: String,
    val product: String,
    val usageType: String,
    val cost: Double,
    val usageStart: String,
    val usageEnd: String,
)
