package com.example.dndcharacterapp.models.spell

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Spell : RealmObject{
    val areaOfEffect: AreaOfEffect? = null
    val attackType: String? = null
    val castingTime: String? = null
    val classes: RealmList<Classe> = realmListOf()
    val components: RealmList<String> = realmListOf()
    val concentration: Boolean? = null
    val damageSpell: DamageSpell? = null
    val dc: Dc? = null
    val description: RealmList<String> = realmListOf()
    val duration: String? = null
    val from: From? = null
    val healAtSlotLevel: HealAtSlotLevel? = null
    val higherLevel: RealmList<String> = realmListOf()
    val id: String? = null
    val index: String? = null
    val level: Int? = null
    val material: String? = null
    val name: String? = null
    val range: String? = null
    val ritual: Boolean? = null
    val subclasses: RealmList<Subclasse> = realmListOf()
}