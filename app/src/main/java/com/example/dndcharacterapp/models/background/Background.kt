package com.example.dndcharacterapp.models.background

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject

class Background : EmbeddedRealmObject{
    val bonds: Bonds? = null
    val feature: Feature? = null
    val flaws: Flaws? = null
    val id: String? = null
    val ideals: Ideals? = null
    val index: String? = null
    val languageOptions: Int? = null
    val name: String? = null
    val personalityTraits: PersonalityTraits? = null
    val startingEquipment: List<StartingEquipment> = realmListOf()
    val startingEquipmentOption: List<StartingEquipmentOption> = realmListOf()
    val startingProficiencies: List<StartingProficiency> = realmListOf()
}