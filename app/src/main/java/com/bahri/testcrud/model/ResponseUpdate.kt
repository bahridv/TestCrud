package com.bahri.testcrud.model

import com.google.gson.annotations.SerializedName

data class ResponseUpdate(
    @SerializedName("title") val title: String,
    @SerializedName("complete") val complete: Boolean,
    @SerializedName("content") val content: String,
    @SerializedName("id") val id: String
)
