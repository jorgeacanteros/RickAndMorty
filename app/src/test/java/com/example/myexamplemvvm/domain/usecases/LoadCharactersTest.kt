package com.example.myexamplemvvm.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.example.myexamplemvvm.data.datasources.CharacterPagingSource
import com.example.myexamplemvvm.data.repositories.CharacterRepository
import com.example.myexamplemvvm.domain.model.CharacterModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import java.util.function.Consumer


class LoadCharactersTest{

  @RelaxedMockK
  private lateinit var repository: CharacterRepository
  private lateinit var loadCharacters: LoadCharacters
  private lateinit var retrofit: Retrofit

  @Before
  fun onBefore(){
    MockKAnnotations.init(this)
    loadCharacters = LoadCharacters(repository)
  }

  @Test
  fun `firt test`(){
    assert(true)
  }

  @Test
  fun `when call AllCharacter from remoteLocate `() = runBlocking{
      //Given
      every { repository.getListAllCharacter() } returns
          Pager(config = PagingConfig(pageSize = 2, maxSize = 200,), pagingSourceFactory = { CharacterPagingSource(retrofit = retrofit) }).flow


      //When
     loadCharacters()
      //Then
      verify (exactly = 1){ repository.getListAllCharacter() }

  }

  @Test
  fun `when get AllCharacter return flow`() {
    //Given
    every { repository.getListAllCharacter() } returns
        Pager(config = PagingConfig(pageSize = 2, maxSize = 200,), pagingSourceFactory = { CharacterPagingSource(retrofit = retrofit) }).flow

    //When
    var response = loadCharacters()

    //Then
    assert(response is Flow)
  }






}