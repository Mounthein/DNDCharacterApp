package com.example.dndcharacterapp.models.level

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Level : RealmObject{
    val abilityScoreBonuses: Int? = null
    val `class`: Class? = null
    val classLevels: Class? = null
    val classSpecific: ClassSpecific? = null
    val features: RealmList<Feature> = realmListOf()
    val id: String? = null
    val index: String? = null
    val levelN: Int? = null
    val proficiencyBonus: Int? = null
    val spellcasting: Spellcasting? = null
    val subcalss: Subcalss? = null
    val subcategories: Subcategories? = null
}