package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmStatCh : EmbeddedRealmObject {
    var name: String? = null
    var value: Int? = null
}