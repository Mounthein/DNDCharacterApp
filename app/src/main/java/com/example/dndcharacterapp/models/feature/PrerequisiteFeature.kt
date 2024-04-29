package com.example.dndcharacterapp.models.feature

import io.realm.kotlin.types.EmbeddedRealmObject

class PrerequisiteFeature : EmbeddedRealmObject{
    val feature: String? = null
    val level: Int? = null
    val spell: String? = null
    val type: String? = null
}