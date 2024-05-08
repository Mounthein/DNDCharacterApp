package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmSpellCh : EmbeddedRealmObject {
    val name: String? = null
    val description: String? = null
    val school: String? = null
    val level: Int? = null
    val casting_time: String? = null
    val duration: String? = null
}