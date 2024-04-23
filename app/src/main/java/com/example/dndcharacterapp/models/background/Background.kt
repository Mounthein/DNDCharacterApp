package com.example.dndcharacterapp.models.background

data class Background(
    val bonds: Bonds,
    val feature: Feature,
    val flaws: Flaws,
    val id: String,
    val ideals: Ideals,
    val index: String,
    val languageOptions: Int,
    val name: String,
    val personalityTraits: PersonalityTraits,
    val startingEquipment: List<StartingEquipment>,
    val startingEquipmentOption: List<StartingEquipmentOption>,
    val startingProficiencies: List<StartingProficiency>
)