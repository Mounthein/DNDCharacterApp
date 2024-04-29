package com.example.dndcharacterapp.models.trait

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class ProficiencyChoices : EmbeddedRealmObject{
    val choose: Int? = null
    val from: RealmList<FromX> = realmListOf()
}