package com.example.dndcharacterapp.models.proficiency

data class Proficiency(
    val classes: List<Classe>,
    val id: String,
    val index: String,
    val name: String,
    val races: List<Race>,
    val reference: Reference,
    val type: String
)