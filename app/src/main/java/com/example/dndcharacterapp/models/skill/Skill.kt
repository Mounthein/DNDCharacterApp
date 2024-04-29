package com.example.dndcharacterapp.models.skill

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Skill : RealmObject{
    val abilityScore: AbilityScore? = null
    val desc: RealmList<String> = realmListOf()
    val id: String? = null
    val index: String? = null
    val name: String? = null
}