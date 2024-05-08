package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmCoinCh : EmbeddedRealmObject {
    var name: String? = null
    var quantity: Int? = null
}