package com.example.dndcharacterapp.models.characterRealm

import io.realm.kotlin.types.EmbeddedRealmObject

class EmRaceCh : EmbeddedRealmObject {
    var name: String? = null
    var speed: String? = null
    var size: String? = null
    var subrace: String? = null
}