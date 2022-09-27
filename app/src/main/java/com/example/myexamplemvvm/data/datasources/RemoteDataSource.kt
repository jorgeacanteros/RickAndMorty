package com.example.myexamplemvvm.data.datasources

import com.example.myexamplemvvm.domain.model.CharacterModel
import javax.inject.Inject

interface RemoteDataSource {
   suspend fun getListCharacter():  List<CharacterModel>
}