package com.example.dndcharacterapp.models.race

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class StartingProficienciesOptions : EmbeddedRealmObject{
    val choose: Int? = null
    val description: String? = null
    val from: RealmList<FromX> = realmListOf()
    val type: String? = null
}