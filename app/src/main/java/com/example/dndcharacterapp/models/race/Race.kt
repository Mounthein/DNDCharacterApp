package com.example.dndcharacterapp.models.race

data class Race(
    val abilityBonus: List<AbilityBonu>,
    val abilityBonusOptions: AbilityBonusOptions,
    val age: String,
    val alignment: String,
    val id: String,
    val index: String,
    val languageDesc: String,
    val languageOptions: LanguageOptions,
    val languages: List<Language>,
    val name: String,
    val size: String,
    val sizeDescription: String,
    val speed: Int,
    val startingProficiencies: List<StartingProficiency>,
    val startingProficienciesOptions: StartingProficienciesOptions,
    val subraces: List<Subrace>,
    val traits: List<Trait>
)