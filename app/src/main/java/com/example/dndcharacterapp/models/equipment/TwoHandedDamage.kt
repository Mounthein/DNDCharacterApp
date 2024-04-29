package com.example.dndcharacterapp.models.equipment

import io.realm.kotlin.types.EmbeddedRealmObject

class TwoHandedDamage : EmbeddedRealmObject{
    val damageDice: String? = null
    val damageType: DamageType? = null
}