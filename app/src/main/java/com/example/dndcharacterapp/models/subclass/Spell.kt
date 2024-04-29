package com.example.dndcharacterapp.models.subclass

import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class Spell : EmbeddedRealmObject{
    val name: RealmList<Name>? = null
    val spell: SpellX? = null
}