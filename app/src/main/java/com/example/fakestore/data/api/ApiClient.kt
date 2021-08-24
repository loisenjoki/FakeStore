package com.example.fakestore.data.api

import com.example.fakestore.BuildConfig
import com.example.fakestore.utils.Constants.Companion.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

        fun getApiService(token: String): ApiService {
            val client = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .callTimeout(1, TimeUnit.MINUTES)
                .addInterceptor { chain -> chain.proceed(injectToken(chain, token)) }
                .addInterceptor(loggingInterceptor())
                .build()
            val retrofitBuilder = Retrofit.Builder()
            //retrofitBuilder.baseUrl(BuildConfig.BASE_URL)
            retrofitBuilder.client(client)
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
            val retrofit: Retrofit = retrofitBuilder.build()
            return retrofit.create(ApiService::class.java)
        }
        fun getApiService(): ApiService {
            val client = OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .callTimeout(1, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor())
                .build()
            val retrofitBuilder = Retrofit.Builder()
            //retrofitBuilder.baseUrl(BuildConfig.BASE_URL)
            retrofitBuilder.client(client)
            retrofitBuilder.addConverterFactory(GsonConverterFactory.create())
            val retrofit: Retrofit = retrofitBuilder.build()
            return retrofit.create(ApiService::class.java)
        }
        private fun loggingInterceptor(): HttpLoggingInterceptor {
            val loggingInterceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            } else {
                loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
            }
            return loggingInterceptor
        }
        private fun injectToken(chain: Interceptor.Chain, token: String): Request {
            return chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        }
    }

