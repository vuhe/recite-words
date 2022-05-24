package top.vuhe.android.network

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://android.zhuhe.site"

val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor {
    Log.e("RetrofitLog", "retrofitBack = $it")
}.setLevel(HttpLoggingInterceptor.Level.BODY)

val client: OkHttpClient = OkHttpClient.Builder()//okhttp设置部分，此处还可再设置网络参数
    .addInterceptor(loggingInterceptor)
    .hostnameVerifier { _, _ -> true }
    .build();

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .baseUrl(BASE_URL)
    .build()

data class Result(
    val code: Int,
    val message: String
)
