package com.example.martyrs.services.http

import com.example.martyrs.data.Comment
import com.example.martyrs.data.Martyr
import com.example.martyrs.data.ResultAddComment
import com.google.gson.JsonObject
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiService {

    @GET("martyr/list")
    fun getMartyrs(
        @Query("PaginationRequestDto.PageSize") pageSize: Int,
        @Query("PaginationRequestDto.PageNumber") pageNumber: Int,
        @Query("SearchText") searchText: String? = null,
        @Query("SortBy") sort: String
    ): Single<Martyr>

    @GET("comment/list/{martyrId}")
    fun getComment(
        @Path("martyrId") martyrId: Int
    ): Single<Comment>

    @POST("comment/create")
    fun addComment(@Body jsonObject: JsonObject)
            : Single<ResultAddComment>

}

fun createApiServiceInstance(): ApiService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val oldRequest = it.request()
            val newRequestBuilder = oldRequest.newBuilder()
            newRequestBuilder.addHeader("accept", "*/*")
            newRequestBuilder.addHeader("Content-Type", "application/json")
            newRequestBuilder.method(oldRequest.method(), oldRequest.body())
            return@addInterceptor it.proceed(newRequestBuilder.build())
        }
        .build()


    val retrofit = Retrofit.Builder()
        .baseUrl("https://saffari.iran.liara.run/")
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        //.client(okHttpClient)
        .build()
    return retrofit.create(ApiService::class.java)
}