package com.example.dndcharacterapp.models.abilityscore.api

data class AbilityScore(
    val description: List<String>,
    val fullName: String,
    val id: String,
    val index: String,
    val name: String,
    val skills: List<ApiSkill>
)