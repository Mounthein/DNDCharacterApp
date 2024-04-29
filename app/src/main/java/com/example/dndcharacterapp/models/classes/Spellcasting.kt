package com.example.dndcharacterapp.models.classes

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class Spellcasting : EmbeddedRealmObject{
    val info: RealmList<Info> = realmListOf()
    val level: Int? = null
    val spellcastingAbility: SpellcastingAbility? = null
}