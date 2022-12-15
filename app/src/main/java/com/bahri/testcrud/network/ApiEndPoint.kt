package com.bahri.testcrud.network

import com.bahri.testcrud.model.ResponseAdd
import com.bahri.testcrud.model.ResponseDelate
import com.bahri.testcrud.model.ResponseList
import com.bahri.testcrud.model.ResponseUpdate
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiEndPoint {
    @GET("todo")
    fun getList(): Call<ResponseList>

    @POST("todo")
    fun addTodo(
        @Body jobj: JsonObject
    ): Call<ResponseAdd>

    @POST("todo")
    fun AddTODOFORMDATA(
        @Field("title") title: String,
        @Field("complete") complete: Boolean,
        @Field("content") content: String,
    ): Call<ResponseAdd>

    @DELETE("todo/{id}")
    fun delateTodo(
        @Path("id") id : String
    ): Call<ResponseDelate>

    @PUT("todo/{id}")
    fun updateTodo(
        @Path("id") id : String,
        @Body jobUpdate: JsonObject
    ): Call<ResponseUpdate>
}