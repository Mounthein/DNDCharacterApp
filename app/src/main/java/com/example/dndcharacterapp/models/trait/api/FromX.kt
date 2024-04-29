package com.example.dndcharacterapp.models.trait.api

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList

data class FromX(
    val choice: ItemsChoiceFeature,
    val index: String,
    val items: ArrayedFrom,
    val name: String
)

data class ItemsChoiceFeature (
    var choose: List<Int>,
    var item: ArrayedFrom
)

data class ArrayedFrom (
    var index: List<String>,
    var name: List<String>
)