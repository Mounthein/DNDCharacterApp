package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmEquipmentCh : EmbeddedRealmObject {
    var name: String? = null
    var equipment_category: String? = null
    var quantity: Int? = null
}