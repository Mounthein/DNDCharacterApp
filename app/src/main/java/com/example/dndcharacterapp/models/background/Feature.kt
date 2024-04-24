package com.example.dndcharacterapp.models.background

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject

class Feature : EmbeddedRealmObject{
    val description: List<String> = realmListOf()
    val name: String? = null
}