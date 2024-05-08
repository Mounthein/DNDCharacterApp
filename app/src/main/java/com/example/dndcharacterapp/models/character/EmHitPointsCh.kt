package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmHitPointsCh : EmbeddedRealmObject {
    var maximum: Int? = null
    var current: Int? = null
    var temporary: Int? = null
}
