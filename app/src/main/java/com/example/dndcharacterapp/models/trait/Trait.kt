package com.example.dndcharacterapp.models.trait

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Trait : RealmObject{
    val description: RealmList<String> = realmListOf()
    val id: String? = null
    val index: String? = null
    val languageOptions: LanguageOptions? = null
    val name: String? = null
    val parent: Parent? = null
    val proficiencies: RealmList<Proficiency>? = null
    val proficiencyChoices: ProficiencyChoices? = null
    val race: RealmList<Race> = realmListOf()
    val subrace: RealmList<Subrace> = realmListOf()
    val traitSpecifics: TraitSpecifics? = null
}