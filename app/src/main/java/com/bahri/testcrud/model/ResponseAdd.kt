package com.bahri.testcrud.model

import com.google.gson.annotations.SerializedName

data class ResponseAdd(
    @SerializedName("title") val title: String,
    @SerializedName("complete") val complete: Boolean,
    @SerializedName("content") val content: String,
    @SerializedName("id") val id: Int
)