package com.example.dndcharacterapp.models.classes

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class MultiClassing : EmbeddedRealmObject{
    val prerequisites: RealmList<Prerequisite> = realmListOf()
    val proficiencies: RealmList<ProficiencyX> = realmListOf()
}