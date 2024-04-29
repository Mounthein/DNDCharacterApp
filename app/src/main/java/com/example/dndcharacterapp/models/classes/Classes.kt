package com.example.dndcharacterapp.models.classes

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Classes : RealmObject{
    val hitDie: Int? = null
    val id: String? = null
    val index: String? = null
    val multiClassing: MultiClassing? = null
    val name: String? = null
    val proficiencies: RealmList<ProficiencyX> = realmListOf()
    val proficienciesChoices: RealmList<ProficienciesChoice> = realmListOf()
    val savingThrows: RealmList<SavingThrow> = realmListOf()
    val spellcasting: Spellcasting? = null
    val startingEquipment: RealmList<StartingEquipment> = realmListOf()
    val startingEquipmentOption: RealmList<StartingEquipmentOption> = realmListOf()
    val subclasses: RealmList<Subclasse> = realmListOf()
}