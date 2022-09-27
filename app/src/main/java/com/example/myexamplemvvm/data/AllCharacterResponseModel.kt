package com.example.myexamplemvvm.data

import com.example.myexamplemvvm.domain.model.CharacterModel
import com.google.gson.annotations.SerializedName

data class AllCharacterResponseModel(@SerializedName("info")   val info: Info,
                                     @SerializedName("results")  val results: List<CharacterModel> )


data class Info (
  val count: Long?,
  val pages: Long?,
  val next: String?,
  val prev: String?
)