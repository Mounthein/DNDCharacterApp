package com.example.dndcharacterapp.models.language

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Language : RealmObject{
    val description: String? = null
    val id: String? = null
    val index: String? = null
    val name: String? = null
    val script: String? = null
    val type: String? = null
    val typicalSpeakesr: RealmList<String> = realmListOf()
}