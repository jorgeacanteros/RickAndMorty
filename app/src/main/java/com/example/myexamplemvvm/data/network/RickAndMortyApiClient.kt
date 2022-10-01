package com.example.myexamplemvvm.data.network

import com.example.myexamplemvvm.data.AllCharacterResponseModel
import com.example.myexamplemvvm.domain.model.CharacterModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiClient {
  @GET("character")
  suspend fun getAllCharacter(@Query("page") query: Int):Response<AllCharacterResponseModel>
}