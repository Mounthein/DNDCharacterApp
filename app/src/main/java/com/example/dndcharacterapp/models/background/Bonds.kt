package com.example.dndcharacterapp.models.background

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject

class Bonds : EmbeddedRealmObject{
    val choose: Int? = null
    val from: List<String> = realmListOf()
}