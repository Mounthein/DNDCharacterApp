package com.example.dndcharacterapp.models.background

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class Alignments : EmbeddedRealmObject{
    var index: RealmList<String> = realmListOf()
    var name: RealmList<String> = realmListOf()
}