package com.example.dndcharacterapp.models.trait

import io.realm.kotlin.types.EmbeddedRealmObject

class Damage : EmbeddedRealmObject{
    val damageCharacterLevel: DamageCharacterLevel? = null
    val damageType: DamageTypeX? = null
}