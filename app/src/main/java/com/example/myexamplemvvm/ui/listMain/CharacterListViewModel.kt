package com.example.myexamplemvvm.ui.listMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myexamplemvvm.data.datasources.RemoteDataSourceImpl
import com.example.myexamplemvvm.data.repositories.CharacterRepository
import com.example.myexamplemvvm.domain.model.CharacterModel
import com.example.myexamplemvvm.domain.usecases.LoadCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterListViewModel @Inject constructor(private val usecaseGetCharacter: LoadCharacters ) : ViewModel() {




    private val _characters = MutableLiveData <List<CharacterModel>>()
    val characters : LiveData<List<CharacterModel>>  get() = _characters

  private val _currentCharacter = MutableLiveData <CharacterModel>()
  val currentCharacter : LiveData<CharacterModel>  get() = _currentCharacter

  fun load() {
    viewModelScope.launch(Dispatchers.Main) {
      _characters.value = usecaseGetCharacter.invoke()
      _currentCharacter.value = _characters.value!!.first()
    }
  }

  fun setCurrentCharacter( character:CharacterModel){
      _currentCharacter.value = character
  }

}