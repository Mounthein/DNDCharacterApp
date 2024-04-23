package com.example.dndcharacterapp.models.race

data class StartingProficienciesOptions(
    val choose: Int,
    val description: String,
    val from: List<FromXX>,
    val type: String
)