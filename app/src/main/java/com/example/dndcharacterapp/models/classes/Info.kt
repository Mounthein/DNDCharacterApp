package com.example.dndcharacterapp.models.classes

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class Info : EmbeddedRealmObject{
    val description: RealmList<String> = realmListOf()
    val name: String? = null
}