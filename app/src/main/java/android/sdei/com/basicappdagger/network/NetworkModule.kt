package android.sdei.com.basicappdagger.network

import android.sdei.com.basicappdagger.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

   // private val BASE_URL = "http://webflowcodes.com/policenaka/index.php/"
   // private val BASE_URL = " http://localhost:3000/"
    private val BASE_URL = "http://139.59.89.13/"

    @Singleton
    @Provides
    internal fun providesOkhttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)


            // add logging as last interceptor

            httpClient.addInterceptor(logging)
        }//


        httpClient.connectTimeout(1, TimeUnit.MINUTES)
        httpClient.readTimeout(1, TimeUnit.MINUTES)
        httpClient.addInterceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json")

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        return httpClient.build()
    }

    @Singleton
    @Provides
    internal fun providesGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Singleton
    @Provides
    internal fun providesRetroFit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()

    }
    @Singleton
    @Provides
    internal  fun providesAPIService(retrofit:Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)

    }
}