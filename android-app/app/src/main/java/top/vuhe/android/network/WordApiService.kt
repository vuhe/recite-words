package top.vuhe.android.network

import retrofit2.http.*

interface WordApiService {
    @GET("/wordbook")
    suspend fun getWordbook(): List<String>

    @GET("/word/{user}")
    suspend fun download(@Path("user") username: String): List<String>

    @POST("/word/{user}")
    suspend fun upload(
        @Path("user") username: String,
        @Body words: List<String>
    ): Result
}

object WordApi {
    val service: WordApiService by lazy { retrofit.create(WordApiService::class.java) }
}