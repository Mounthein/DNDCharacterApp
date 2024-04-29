package com.example.dndcharacterapp.models.trait

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class BreathWeapon : EmbeddedRealmObject{
    val areaOfEffect: AreaOfEffect? = null
    val damage: RealmList<Damage> = realmListOf()
    val dc: Dc? = null
    val description: String? = null
    val name: String? = null
    val usage: Usage? = null
}