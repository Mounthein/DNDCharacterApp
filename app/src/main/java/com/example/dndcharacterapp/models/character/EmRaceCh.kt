package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmRaceCh : EmbeddedRealmObject {
    var name: String? = null
    var speed: Int? = null
    var size: String? = null
    var subrace: String? = null
}