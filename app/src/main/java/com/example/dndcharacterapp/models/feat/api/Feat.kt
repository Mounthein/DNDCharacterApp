package com.example.dndcharacterapp.models.feat.api

data class Feat(
    val description: List<String>,
    val id: String,
    val index: String,
    val name: String,
    val prerequisites: List<Prerequisite>
)