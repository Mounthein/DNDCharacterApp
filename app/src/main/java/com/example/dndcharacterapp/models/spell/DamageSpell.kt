package com.example.dndcharacterapp.models.spell

data class DamageSpell(
    val damageAtCharacterLevel: DamageAtCharacterLevel?,
    val damageSlotLevel: DamageSlotLevel?,
    val damageType: DamageType?
)