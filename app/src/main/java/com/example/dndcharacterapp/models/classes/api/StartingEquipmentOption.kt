package com.example.dndcharacterapp.models.classes.api

data class StartingEquipmentOption(
    val choose: Int,
    val description: String,
    val from: List<List<FromX>>
)