package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmClassItemCh : EmbeddedRealmObject {
    var name: String? = null
    var level: Int? = null
    var subclass: String? = null
}