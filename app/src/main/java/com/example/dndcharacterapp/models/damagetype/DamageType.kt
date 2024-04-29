package com.example.dndcharacterapp.models.damagetype

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class DamageType : RealmObject{
    val description: RealmList<String> = realmListOf()
    val id: String? = null
    val index: String? = null
    val name: String? = null
}