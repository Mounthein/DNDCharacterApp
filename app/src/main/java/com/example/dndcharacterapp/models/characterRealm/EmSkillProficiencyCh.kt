package com.example.dndcharacterapp.models.characterRealm

import io.realm.kotlin.types.EmbeddedRealmObject

class EmSkillProficiencyCh : EmbeddedRealmObject {
    var name: String? = null
    var bonus: Double? = null
}