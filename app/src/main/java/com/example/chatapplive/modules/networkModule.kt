package com.example.chatapplive.modules

import com.example.chatapplive.api.apiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class networkModule{

    companion object {
        private const val BASE_URL = "http://10.0.2.2:2948/";
    }

    @Provides
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(networkModule.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun getApiInstance(retrofit: Retrofit) : apiService {
        return retrofit.create(apiService::class.java)
    }

}