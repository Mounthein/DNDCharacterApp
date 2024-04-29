package com.example.dndcharacterapp.models.classes

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class StartingEquipmentOption : EmbeddedRealmObject {
    val choose: Int? = null
    val description: String? = null
    val from: RealmList<List<FromX>> = realmListOf()
}