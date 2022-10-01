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
class CharacterListViewModel @Inject constructor(private val usecaseGetCharacter: LoadCharacters ) : ViewModel() {

  //  private val _characters = MutableLiveData <List<CharacterModel>>()
   // val characters : LiveData<List<CharacterModel>>  get() = _characters

 // val characters :Flow<PagingData<CharacterModel>> = Pager( config = PagingConfig(pageSize = 20, maxSize = 200){

  //},
  private val _currentCharacter = MutableLiveData <CharacterModel>()
  val currentCharacter : LiveData<CharacterModel>  get() = _currentCharacter

  private val _uiState = MutableStateFlow(CharacterUiState.Success(null))
  val uiState: StateFlow<CharacterUiState> = _uiState

 /* fun loadCharacters():Flow<PagingData<CharacterModel>>{
    return Pager( config = PagingConfig(pageSize = 2, maxSize = 200,),
      pagingSourceFactory = {CharacterPagingSource(retrofit = provideRetrofit())}).flow.cachedIn(viewModelScope)
  }*/
   var characters : Flow<PagingData<CharacterModel>> = usecaseGetCharacter.invoke().cachedIn(viewModelScope)
   /*userRepository.getUsers()
     .map { pagingData ->
       pagingData.map { userModel -> UserItemUiState(userModel) }
     }.cachedIn(viewModelScope)
   usecaseGetCharacter.invoke().flow.cachedIn(viewModelScope)*/

  fun loadCharacters(){
   viewModelScope.launch(Dispatchers.IO) {
     //  _characters.value = usecaseGetCharacter.invoke()
     // _currentCharacter.value = _characters.value!!.first()

   }

 }


  fun load() {
    viewModelScope.launch(Dispatchers.Main) {
    //  _characters.value = usecaseGetCharacter.invoke()
     // _currentCharacter.value = _characters.value!!.first()
    }
  }

  fun setCurrentCharacter( character:CharacterModel){
      _currentCharacter.value = character
  }

}

sealed class CharacterUiState {
  object Progress: CharacterUiState()
  data class Success(val charactersList: Flow<PagingData<CharacterModel>>?): CharacterUiState()
  data class Error(val exception: Throwable): CharacterUiState()
}