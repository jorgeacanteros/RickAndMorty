package com.example.myexamplemvvm.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.example.myexamplemvvm.data.datasources.CharacterPagingSource
import com.example.myexamplemvvm.data.datasources.RemoteDataSource
import com.example.myexamplemvvm.di.NetworkModule
import com.example.myexamplemvvm.domain.model.CharacterModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharacterRepository @Inject constructor( private val dataSource: CharacterPagingSource) {

  fun getListAllCharacter():Flow<PagingData<CharacterModel>> =
    Pager( config = PagingConfig(pageSize = 1, maxSize = 200,),
      pagingSourceFactory ={dataSource} ).flow



}