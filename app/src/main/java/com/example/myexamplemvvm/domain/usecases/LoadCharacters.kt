package com.example.myexamplemvvm.domain.usecases

import com.example.myexamplemvvm.data.repositories.CharacterRepository
import com.example.myexamplemvvm.domain.model.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoadCharacters @Inject constructor(private val CharacterRepository:CharacterRepository) {

  suspend fun invoke():List <CharacterModel> =  withContext(Dispatchers.IO){
    CharacterRepository.getListAllCharacter()
  }
}