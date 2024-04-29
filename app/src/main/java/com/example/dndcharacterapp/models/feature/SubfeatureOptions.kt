package com.example.dndcharacterapp.models.feature

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class SubfeatureOptions : EmbeddedRealmObject{
    val choose: Int? = null
    val from: RealmList<FromX> = realmListOf()
}