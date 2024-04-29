package com.example.dndcharacterapp.models.classes

import io.realm.kotlin.types.EmbeddedRealmObject

class Prerequisite : EmbeddedRealmObject{
    val abilityScore: AbilityScore? = null
    val minimumScore: Int? = null
}