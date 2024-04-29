package com.example.dndcharacterapp.models.background

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class Ideals : EmbeddedRealmObject{
    var choose: Int? = null
    var from: RealmList<From> = realmListOf()
    var type: String? = null
}