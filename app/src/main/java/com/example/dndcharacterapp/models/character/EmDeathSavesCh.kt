package com.example.dndcharacterapp.models.character

import io.realm.kotlin.types.EmbeddedRealmObject

class EmDeathSavesCh : EmbeddedRealmObject {
    var success: Int? = null
    var failures: Int? = null
}