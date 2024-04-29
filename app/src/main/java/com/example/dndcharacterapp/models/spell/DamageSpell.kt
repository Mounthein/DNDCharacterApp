package com.example.dndcharacterapp.models.spell

import io.realm.kotlin.types.EmbeddedRealmObject

class DamageSpell : EmbeddedRealmObject{
    val damageAtCharacterLevel: DamageAtCharacterLevel? = null
    val damageSlotLevel: DamageSlotLevel? = null
    val damageType: DamageType? = null
}