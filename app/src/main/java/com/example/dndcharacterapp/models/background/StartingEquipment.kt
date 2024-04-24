package com.example.dndcharacterapp.models.background

import io.realm.kotlin.types.EmbeddedRealmObject

class StartingEquipment : EmbeddedRealmObject{
    val equipment: Equipment? = null
    val quantity: Int? = null
}