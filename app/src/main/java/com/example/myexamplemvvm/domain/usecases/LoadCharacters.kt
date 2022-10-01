package com.example.myexamplemvvm.domain.usecases



import androidx.paging.PagingData

import com.example.myexamplemvvm.data.repositories.CharacterRepository
import com.example.myexamplemvvm.domain.model.CharacterModel

import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class LoadCharacters @Inject constructor(private val CharacterRepository:CharacterRepository) {

 operator fun invoke(): Flow<PagingData<CharacterModel>> = CharacterRepository.getListAllCharacter()

}