package com.example.dndcharacterapp.models.race

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Race : RealmObject{
    val abilityBonus: RealmList<AbilityBonu> = realmListOf()
    val abilityBonusOptions: AbilityBonusOptions? = null
    val age: String? = null
    val alignment: String? = null
    val id: String? = null
    val index: String? = null
    val languageDesc: String? = null
    val languageOptions: LanguageOptions? = null
    val languages: RealmList<Language> = realmListOf()
    val name: String? = null
    val size: String? = null
    val sizeDescription: String? = null
    val speed: Int? = null
    val startingProficiencies: RealmList<StartingProficiency> = realmListOf()
    val startingProficienciesOptions: StartingProficienciesOptions? = null
    val subraces: RealmList<Subrace> = realmListOf()
    val traits: RealmList<Trait> = realmListOf()
}