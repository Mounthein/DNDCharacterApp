package com.example.dndcharacterapp.models.subclass

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Subclass : RealmObject{
    val `class`: Class? = null
    val description: RealmList<String> = realmListOf()
    val id: String? = null
    val index: String? = null
    val name: String? = null
    val spells: RealmList<Spell> = realmListOf()
    val subclassFlavor: String? = null
    val subclassLevels: String? = null
}