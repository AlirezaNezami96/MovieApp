package alireza.nezami.network.di

import alireza.nezami.network.interceptors.ApiKeyInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val HTTP_READ_TIMEOUT_IN_SECONDS = 60
private const val HTTP_CALL_TIMEOUT_IN_SECONDS = 60

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val baseUrl = Config.BACKEND_URL

    @Singleton
    @Provides
    fun provideOkHttpLogger() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideApiKeyInterceptor() =
        ApiKeyInterceptor(Config.API_KEY)


    @Singleton
    @Provides
    fun provideOkHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val okHttpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        return okHttpClient
            .addInterceptor(apiKeyInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .readTimeout(HTTP_READ_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
            .callTimeout(HTTP_CALL_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json { ignoreUnknownKeys = true }
        val converterFactory = json.asConverterFactory(contentType)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }
}
