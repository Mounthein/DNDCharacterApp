package com.example.dndcharacterapp.models.classes

data class ProficienciesChoice(
    val choose: Int,
    val description: String,
    val from: List<From>
)