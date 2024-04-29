package com.example.dndcharacterapp.models.background

import io.realm.kotlin.types.EmbeddedRealmObject

class StartingEquipment : EmbeddedRealmObject{
    var equipment: Equipment? = null
    var quantity: Int? = null
}