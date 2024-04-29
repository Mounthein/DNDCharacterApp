package com.example.dndcharacterapp.models.equipment

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class Contents : EmbeddedRealmObject{
    val item: RealmList<Item> = realmListOf()
    val quantity: Int? = null
}