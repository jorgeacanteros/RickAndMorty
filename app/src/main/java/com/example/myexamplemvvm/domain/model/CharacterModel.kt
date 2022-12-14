package com.example.myexamplemvvm.domain.model

import com.google.gson.annotations.SerializedName

data class CharacterModel(val id: Long?,
                          @SerializedName("name")val name: String?,
                          @SerializedName("status")val status: String?,
                          val species: String?,
                          val type: String?,
                          @SerializedName("gender") val gender: String?,
                          val origin: Origin,
                          val location: Location,
                          @SerializedName("image")val image: String?,
                          val episode: List<String>?,
                          val url: String?,
                          val created: String?)

data class Origin(val name: String?, val url: String?)

data class Location(val name: String?, val url: String? )