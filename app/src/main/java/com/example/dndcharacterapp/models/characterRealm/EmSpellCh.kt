package com.example.dndcharacterapp.models.characterRealm

import io.realm.kotlin.types.EmbeddedRealmObject

class EmSpellCh : EmbeddedRealmObject {
    var name: String? = null
    var description: String? = null
    var school: String? = null
    var level: Int? = null
    var casting_time: String? = null
    var duration: String? = null
}