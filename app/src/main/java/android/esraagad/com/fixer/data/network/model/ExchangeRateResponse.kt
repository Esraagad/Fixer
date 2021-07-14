package android.esraagad.com.fixer.data.network.model

data class ExchangeRateResponse(
    val success: Boolean,
    val timestamp: Int,
    val base: String,
    val date: String,
    val rates: Map<String, Double>
)
