package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmHitDieCh : EmbeddedRealmObject {
    var type: String? = null
    var quantity: Int? = null
}

