package top.vuhe.android.network

import retrofit2.http.*
import top.vuhe.android.entity.User

interface UserApiService {
    @POST("/login")
    suspend fun login(@Body user: User): Result

    @POST("/logon")
    suspend fun register(@Body user: User): Result

    @GET("/log")
    suspend fun userLog(): List<String>
}

object UserApi {
    val service: UserApiService by lazy { retrofit.create(UserApiService::class.java) }
}