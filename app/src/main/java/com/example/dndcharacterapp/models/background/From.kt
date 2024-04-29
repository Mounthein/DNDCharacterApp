package com.example.dndcharacterapp.models.background

import com.example.dndcharacterapp.models.background.Alignments
import io.realm.kotlin.types.EmbeddedRealmObject

class From : EmbeddedRealmObject{
    var alignments: Alignments? = null
    var description: String? = null
}