package com.swapnil.movieapp.di

import android.app.Application
import androidx.room.Room
import com.google.gson.GsonBuilder
import com.swapnil.movieapp.model.network.service.MovieApiService
import com.swapnil.movielistapp.model.persistence.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieAppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(MovieApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    @Singleton
    fun provideMovieApiService(retrofitService: Retrofit): MovieApiService =
        retrofitService.create(MovieApiService::class.java)

    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application) = Room.databaseBuilder(
        app,
        MovieDatabase::class.java,
        "movies_database"
    ).build()

}