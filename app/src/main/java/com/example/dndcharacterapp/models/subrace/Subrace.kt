package com.example.dndcharacterapp.models.subrace

data class Subrace(
    val abilityBonuses: List<AbilityBonuse>,
    val description: String,
    val id: String,
    val index: String,
    val languageOptions: LanguageOptions,
    val languages: List<From>,
    val name: String,
    val race: Race,
    val raceTraits: List<RaceTrait>,
    val racialTraits: List<RacialTrait>
)