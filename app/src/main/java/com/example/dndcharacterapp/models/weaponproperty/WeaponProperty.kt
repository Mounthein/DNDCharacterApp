package com.example.dndcharacterapp.models.weaponproperty

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class WeaponProperty : RealmObject {
    val description: RealmList<String> = realmListOf()
    val id: String? = null
    val index: String? = null
    val name: String? = null
}