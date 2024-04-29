package com.example.dndcharacterapp.models.classes.api

data class Spellcasting(
    val info: List<Info>,
    val level: Int,
    val spellcastingAbility: SpellcastingAbility
)