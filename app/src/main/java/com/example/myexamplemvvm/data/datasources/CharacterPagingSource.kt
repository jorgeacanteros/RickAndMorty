package com.example.myexamplemvvm.data.datasources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myexamplemvvm.data.network.RickAndMortyApiClient
import com.example.myexamplemvvm.domain.model.CharacterModel
import kotlinx.coroutines.delay
import retrofit2.Retrofit
import javax.inject.Inject


class CharacterPagingSource @Inject constructor(private val retrofit: Retrofit): PagingSource<Int, CharacterModel>() {

  override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
    return state.anchorPosition
  }

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
    return try {
      val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
      delay(1000)
      val response = retrofit.create(RickAndMortyApiClient::class.java).getAllCharacter(nextPage)

      var nextPageNumber: Int? = null
      if(response?.body()?.info?.next != null) {
        val uri = Uri.parse(response?.body()?.info?.next!!)
        val nextPageQuery = uri.getQueryParameter("page")
        nextPageNumber = nextPageQuery?.toInt()
      }
      var prevPageNumber: Int? = null
      if(response?.body()?.info?.prev != null) {
        val uri = Uri.parse(response?.body()?.info?.prev!!)
        val prevPageQuery = uri.getQueryParameter("page")

        prevPageNumber = prevPageQuery?.toInt()
      }

      LoadResult.Page(data = response.body()?.results!!,
        prevKey = prevPageNumber,
        nextKey = nextPageNumber)
    }
    catch (e: Exception) {
      LoadResult.Error(e)
    }
  }
  companion object {
    private const val FIRST_PAGE_INDEX = 1
  }



}