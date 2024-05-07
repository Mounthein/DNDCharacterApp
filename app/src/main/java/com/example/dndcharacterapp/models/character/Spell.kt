package com.example.dndcharacterapp.models.character

data class Spell(
    val name: String? = null,
    val description: String? = null,
    val school: String? = null,
    val level: Int? = null,
    val casting_time: String? = null,
    val duration: String? = null
)
