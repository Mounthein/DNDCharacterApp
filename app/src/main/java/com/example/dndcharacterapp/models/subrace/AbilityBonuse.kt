package com.example.dndcharacterapp.models.subrace

import io.realm.kotlin.types.EmbeddedRealmObject

class AbilityBonuse : EmbeddedRealmObject{
    val abilityScore: AbilityScore? = null
    val bonus: Int? = null
}