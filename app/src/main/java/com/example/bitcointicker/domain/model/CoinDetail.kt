package com.example.bitcointicker.domain.model

import com.google.gson.annotations.SerializedName

data class CoinDetail(
    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String? = "",
    val name: String? = "",
)
