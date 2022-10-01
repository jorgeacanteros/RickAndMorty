package com.example.myexamplemvvm.ui.listMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myexamplemvvm.data.datasources.CharacterPagingSource
import com.example.myexamplemvvm.di.NetworkModule.provideRetrofit

import com.example.myexamplemvvm.domain.model.CharacterModel
import com.example.myexamplemvvm.domain.usecases.LoadCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor( useCaseGetCharacter: LoadCharacters ) : ViewModel() {

  private val _currentCharacter = MutableLiveData <CharacterModel>()
  val currentCharacter : LiveData<CharacterModel>  get() = _currentCharacter

   var characters : Flow<PagingData<CharacterModel>> = useCaseGetCharacter.invoke().cachedIn(viewModelScope)

  fun setCurrentCharacter( character:CharacterModel){
    _currentCharacter.value = character
  }
 }

