package com.example.dndcharacterapp.models.character

data class Spell(
    val name: String? = null,
    val description: String? = null,
    val school: String? = null,
    val level: Int? = null,
    val castingTime: String? = null,
    val duration: String? = null
)