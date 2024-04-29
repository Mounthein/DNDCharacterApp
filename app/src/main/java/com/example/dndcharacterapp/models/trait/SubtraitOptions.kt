package com.example.dndcharacterapp.models.trait

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList

class SubtraitOptions{
    val choose: Int? = null
    val from: RealmList<FromX> = realmListOf()
}