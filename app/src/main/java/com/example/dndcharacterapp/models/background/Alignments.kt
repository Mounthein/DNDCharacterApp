package com.example.dndcharacterapp.models.background

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject

class Alignments : EmbeddedRealmObject{
    val index: List<String> = realmListOf()
    val name: List<String> = realmListOf()
}