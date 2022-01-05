package com.example.myapplication.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoffeeModel(
    var id          : Int,
    var uid         : String,
    @SerialName("blend_name")
    var blendName   : String,
    var origin      : String,
    var variety     : String,
    var notes       : String,
    var intensifier : String,
)
{
    fun toPrettyString(): String {
        return "Blend: $blendName\nOrigin: $origin\nVariety: $variety\nNotes: $notes\nIntensifier: $intensifier\n"
    }
}