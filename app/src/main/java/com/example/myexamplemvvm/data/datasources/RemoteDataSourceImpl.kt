package com.example.myexamplemvvm.data.datasources

import com.example.myexamplemvvm.core.RetrofitHelper
import com.example.myexamplemvvm.data.network.RickAndMortyApiClient
import com.example.myexamplemvvm.domain.model.CharacterModel
import retrofit2.Retrofit
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val retrofit: Retrofit ): RemoteDataSource {

  override suspend fun getListCharacter(): List<CharacterModel> {
    val response = retrofit.create(RickAndMortyApiClient::class.java).getAllCharacter(1)
      return response.body()?.results ?: emptyList()
  }
}