package com.example.dndcharacterapp.models.feature

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class ChoiceX : EmbeddedRealmObject{
    val choose: RealmList<Int> = realmListOf()
    val item: RealmList<FromX> = realmListOf()
}