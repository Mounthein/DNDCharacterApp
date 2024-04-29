package com.example.dndcharacterapp.models.proficiency

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Proficiency: RealmObject{
    val classes: RealmList<Classe> = realmListOf()
    val id: String? = null
    val index: String? = null
    val name: String? = null
    val races: RealmList<Race> = realmListOf()
    val reference: Reference? = null
    val type: String? = null
}