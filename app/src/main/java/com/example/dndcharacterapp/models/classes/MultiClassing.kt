package com.example.dndcharacterapp.models.classes

data class MultiClassing(
    val prerequisites: List<Prerequisite>,
    val proficiencies: List<ProficiencyX>
)