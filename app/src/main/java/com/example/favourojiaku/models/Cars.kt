package com.example.favourojiaku.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Cars (
        @SerializedName("id") val id: String,
        @SerializedName("first_name")val first_name : String,
        @SerializedName("last_name") val last_name : String,
        @SerializedName("email")val email : String,
        @SerializedName("country")val country : String,
        @SerializedName("car_model")val car_model : String,
        @SerializedName("car_model_year")val car_model_year : String,
        @SerializedName("car_color")val car_color : String,
        @SerializedName("gender")val gender : String,
        @SerializedName("job_title")val job_title : String,
        @SerializedName("bio")val bio : String
)
{
        @PrimaryKey(autoGenerate = true)
        var system_id: Int = 0
}