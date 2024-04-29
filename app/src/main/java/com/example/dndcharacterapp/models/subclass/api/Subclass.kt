package com.example.dndcharacterapp.models.subclass.api

data class Subclass(
    val `class`: Class,
    val description: List<String>,
    val id: String,
    val index: String,
    val name: String,
    val spells: List<Spell>,
    val subclassFlavor: String,
    val subclassLevels: String
)