package com.example.dndcharacterapp.models.background

import io.realm.kotlin.types.EmbeddedRealmObject

class StartingEquipmentOption : EmbeddedRealmObject{
    var choose: Int? = null
    var from: FromX? = null
    var type: String? = null
}