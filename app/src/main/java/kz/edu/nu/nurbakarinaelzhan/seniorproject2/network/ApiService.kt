package kz.edu.nu.nurbakarinaelzhan.seniorproject2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.data.NewUser
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.data.User
import kz.edu.nu.nurbakarinaelzhan.seniorproject2.data.UserCredential
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST


//private const val BASE_URL = "http://138.68.86.48/"
private const val BASE_URL = "http://192.168.43.92:4000/"

fun createRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    return Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(client)
        .baseUrl(BASE_URL)
        .build()
}

interface ApiService {
    /**
     * Returns a Coroutine [List] of [User] which can be fetched with await() if in a Coroutine scope.
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */

    @POST("login")
    suspend fun login(@Body credentials: UserCredential): User

    @POST("register")
    suspend fun register(@Body user: NewUser): User
}

object Api {
    val backend : ApiService by lazy { createRetrofit().create(ApiService::class.java) }
}