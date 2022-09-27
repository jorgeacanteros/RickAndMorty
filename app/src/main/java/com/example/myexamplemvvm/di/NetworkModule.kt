package com.example.myexamplemvvm.di

import com.example.myexamplemvvm.data.datasources.RemoteDataSource
import com.example.myexamplemvvm.data.datasources.RemoteDataSourceImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @Singleton
  @Provides
  fun provideRetrofit():Retrofit{
    return Retrofit.Builder()
      .baseUrl("https://rickandmortyapi.com/api/")
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Singleton
  @Provides
  fun provideApi(retrofit: Retrofit): RemoteDataSource {
    return  RemoteDataSourceImpl(retrofit);
  }

}