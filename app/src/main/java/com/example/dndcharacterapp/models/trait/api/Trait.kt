package com.example.dndcharacterapp.models.trait.api

data class Trait(
    val description: List<String>,
    val id: String,
    val index: String,
    val languageOptions: LanguageOptions,
    val name: String,
    val parent: Parent,
    val proficiencies: List<Proficiency>,
    val proficiencyChoices: ProficiencyChoices,
    val race: List<Race>,
    val subrace: List<Subrace>,
    val traitSpecifics: TraitSpecifics
)