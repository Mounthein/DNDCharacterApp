package com.example.dndcharacterapp.models.skill

data class Skill(
    val abilityScore: AbilityScore,
    val desc: List<String>,
    val id: String,
    val index: String,
    val name: String
)