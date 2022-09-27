package com.example.myexamplemvvm.data.repositories

import com.example.myexamplemvvm.data.datasources.RemoteDataSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val dataSource: RemoteDataSource) {
       suspend fun getListAllCharacter() = dataSource.getListCharacter()
}