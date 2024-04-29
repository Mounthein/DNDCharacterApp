package com.example.dndcharacterapp.models.race

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LanguageOptions : EmbeddedRealmObject{
    val choose: Int? = null
    val from: RealmList<FromX> = realmListOf()
    val type: String? = null
}