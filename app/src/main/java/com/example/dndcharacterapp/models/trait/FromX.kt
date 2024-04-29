package com.example.dndcharacterapp.models.trait

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

class FromX : EmbeddedRealmObject{
    val choice: LanguageOptions? = null
    val index: String? = null
    val items: ItemsChoiceFeature? = null
    val name: String? = null
}

class ItemsChoiceFeature : EmbeddedRealmObject{
    var choose: RealmList<Int> = realmListOf()
    var item: ArrayedFrom? = null
}

class ArrayedFrom : EmbeddedRealmObject{
    var index: RealmList<String> = realmListOf()
    var name: RealmList<String> = realmListOf()
}