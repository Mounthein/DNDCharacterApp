package com.example.dndcharacterapp.models.classes.api

data class ProficienciesChoice(
    val choose: Int,
    val description: String,
    val from: List<From>
)