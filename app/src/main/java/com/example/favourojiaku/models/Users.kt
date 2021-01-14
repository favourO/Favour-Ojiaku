package com.example.favourojiaku.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Users (
    @SerializedName("id") val id: String,
    @SerializedName("avatar")val avatar : String,
    @SerializedName("fullName") val fullName : String,
    @SerializedName("createdAt") val createdAt : String,
    @SerializedName("gender") val gender : String
    /*
    @SerializedName("colors")val colors : List<String>,
    @SerializedName("countries")val countries : List<String>*/
)
{
    @PrimaryKey(autoGenerate = true)
    var system_id: Int = 0
}

