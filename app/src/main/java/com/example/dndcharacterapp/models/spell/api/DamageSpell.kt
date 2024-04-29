package com.example.dndcharacterapp.models.spell.api

data class DamageSpell(
    val damageAtCharacterLevel: DamageAtCharacterLevel,
    val damageSlotLevel: DamageSlotLevel,
    val damageType: DamageType
)