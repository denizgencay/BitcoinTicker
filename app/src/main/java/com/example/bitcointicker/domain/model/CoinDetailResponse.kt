package com.example.bitcointicker.domain.model

import com.google.gson.annotations.SerializedName

data class CoinDetail (

    var id: String? = "",
    var symbol: String? = "",
    var name: String? = "",
    var description: Description? = Description(),
    var image: Image? = Image(),
    @SerializedName("hashing_algorithm") var hashingAlgorithm: String? = "",
    @SerializedName("market_data") var marketData: MarketData? = MarketData(),
)

data class CurrentPrice (
    var usd  : Double? = 0.0,
)

data class Description (
    var en: String? = null,
)

data class Image (
    var thumb : String? = "",
    var small : String? = "",
    var large : String? = ""
)

data class MarketData (
    @SerializedName("current_price") var currentPrice: CurrentPrice? = CurrentPrice(),
    @SerializedName("price_change_24h") var priceChange24h: Double?  = 0.0,
    @SerializedName("price_change_percentage_24h") var priceChangePercentage24h: Double? = 0.0,
)