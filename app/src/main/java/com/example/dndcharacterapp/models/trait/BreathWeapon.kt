package com.example.dndcharacterapp.models.trait

data class BreathWeapon(
    val areaOfEffect: AreaOfEffect,
    val damage: List<Damage>,
    val dc: Dc,
    val description: String,
    val name: String,
    val usage: Usage
)