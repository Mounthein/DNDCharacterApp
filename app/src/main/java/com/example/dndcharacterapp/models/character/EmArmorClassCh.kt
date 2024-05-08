package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmArmorClassCh : EmbeddedRealmObject {
    var name: String? = null
    var type: String? = null
    var value: Int? = null
}