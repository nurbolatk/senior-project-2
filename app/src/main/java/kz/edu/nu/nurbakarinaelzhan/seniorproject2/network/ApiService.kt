package kz.edu.nu.nurbakarinaelzhan.seniorproject2.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//private const val BASE_URL = "http://192.168.42.43:4000/" // 4G
//private const val BASE_URL = "http://192.168.1.178:4000/" // home
//private const val BASE_URL = "http://10.194.173.35:4000/" // jysan
private const val BASE_URL = "http://138.68.86.48/" // remote

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
    suspend fun login(@Body credentials: UserCredentials): NetworkUser

    @POST("register")
    suspend fun register(@Body user: NewUser): NetworkUser

    @POST("symptoms")
    suspend fun sendSymptoms(@Body symptomsPayload: SymptomsPayload)

    @GET("isPredictionReady")
    suspend fun getPrediction(@Query("id") id: String): PredictionGet

    @GET("getHealthInfo")
    suspend fun getStatus(@Query("id") id: String): PredictionStatus

    @GET("pulseoximeter")
    suspend fun spo2(@Query("s") intSpo2: Int, @Query("i") id: String)

    @GET("thermometer")
    suspend fun thermometer(@Query("s") temp: Int, @Query("i") id: String)

    @GET("spirometer")
    suspend fun spirometer(@Query("s") fev1: Int, @Query("i") id: String)
}

enum class ApiStatus {
    IDLE, LOADING, SUCCESS, ERROR
}