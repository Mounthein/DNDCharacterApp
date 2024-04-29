package com.example.dndcharacterapp.models.subrace

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class LanguageOptions : EmbeddedRealmObject{
    val choose: Int? = null
    val from: RealmList<From> = realmListOf()
}