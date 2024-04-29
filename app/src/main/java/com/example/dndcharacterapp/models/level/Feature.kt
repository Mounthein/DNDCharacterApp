package com.example.dndcharacterapp.models.level

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class Feature : EmbeddedRealmObject{
    val index: RealmList<String> = realmListOf()
    val name: RealmList<String> = realmListOf()
}