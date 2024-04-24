package com.example.dndcharacterapp.models.background

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject

class Ideals : EmbeddedRealmObject{
    val choose: Int? = null
    val from: List<From> = realmListOf()
    val type: String? = null
}