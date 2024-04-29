package com.example.dndcharacterapp.models.background

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class Feature : EmbeddedRealmObject{
    var description: RealmList<String> = realmListOf()
    var name: String? = null
}