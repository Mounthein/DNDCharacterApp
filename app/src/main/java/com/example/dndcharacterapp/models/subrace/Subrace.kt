package com.example.dndcharacterapp.models.subrace

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Subrace : RealmObject{
    val abilityBonuses: List<AbilityBonuse> = realmListOf()
    val description: String? = null
    val id: String? = null
    val index: String? = null
    val languageOptions: LanguageOptions? = null
    val languages: RealmList<Race> = realmListOf()
    val name: String? = null
    val race: Race? = null
    val raceTraits: RealmList<RaceTrait> = realmListOf()
    val racialTraits: RealmList<RacialTrait> = realmListOf()
}