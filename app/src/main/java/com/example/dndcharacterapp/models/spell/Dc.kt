package com.example.dndcharacterapp.models.spell

import io.realm.kotlin.types.EmbeddedRealmObject

class Dc : EmbeddedRealmObject{
    val dc_desc: String? = null
    val dc_success: String? = null
    val dc_type: DcType? = null
}