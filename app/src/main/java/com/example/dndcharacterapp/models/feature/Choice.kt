package com.example.dndcharacterapp.models.feature

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class Choice : EmbeddedRealmObject{
    val choose: Int? = null
    val froms: RealmList<FromX> = realmListOf()
}