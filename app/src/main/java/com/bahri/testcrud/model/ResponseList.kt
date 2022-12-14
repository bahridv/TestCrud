package com.bahri.testcrud.model

import com.google.gson.annotations.SerializedName

data class ResponseList(
    @SerializedName("data") val data: List<DataProduk>
)
data class DataProduk(
    @SerializedName("title") val title : String,
    @SerializedName("complete") val complete : Boolean,
    @SerializedName("content") val content : String,
    @SerializedName("id") val id : Int,
)
