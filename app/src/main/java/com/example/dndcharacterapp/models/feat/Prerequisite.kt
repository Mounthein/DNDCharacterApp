package com.example.dndcharacterapp.models.feat

import io.realm.kotlin.types.EmbeddedRealmObject

class Prerequisite : EmbeddedRealmObject{
    val abilityScore: AbilityScore? = null
    val minimumScore: Int? = null
}