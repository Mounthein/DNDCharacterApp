package com.example.dndcharacterapp.models.classes.api

data class MultiClassing(
    val prerequisites: List<Prerequisite>,
    val proficiencies: List<ProficiencyX>
)