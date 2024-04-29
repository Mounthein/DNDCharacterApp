package com.example.dndcharacterapp.models.race

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class AbilityBonusOptions : EmbeddedRealmObject{
    val choose: Int? = null
    val from: RealmList<From> = realmListOf()
    val type: String? = null
}