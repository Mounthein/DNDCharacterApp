package com.example.dndcharacterapp.models.conditions

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Condition : RealmObject{
    val desc: RealmList<String> = realmListOf()
    val id: String? = null
    val index: String? = null
    val name: String? = null
}