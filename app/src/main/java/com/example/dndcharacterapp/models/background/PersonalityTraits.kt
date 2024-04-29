package com.example.dndcharacterapp.models.background

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class PersonalityTraits : EmbeddedRealmObject{
    var choose: Int? = null
    var from: RealmList<String> = realmListOf()
}