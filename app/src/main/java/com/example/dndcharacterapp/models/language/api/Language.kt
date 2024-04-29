package com.example.dndcharacterapp.models.language.api

data class Language(
    val description: String,
    val id: String,
    val index: String,
    val name: String,
    val script: String,
    val type: String,
    val typicalSpeakesr: List<String>
)