package com.anishhegde.soccermania.di

import com.anishhegde.soccermania.home.network.SoccerApi
import com.anishhegde.soccermania.home.network.SoccerService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://www.thesportsdb.com/"

    @Provides
    fun provideCountriesApi(): SoccerApi {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SoccerApi::class.java)
    }

    @Provides
    fun provideSoccerService(): SoccerService {
        return SoccerService()
    }
}