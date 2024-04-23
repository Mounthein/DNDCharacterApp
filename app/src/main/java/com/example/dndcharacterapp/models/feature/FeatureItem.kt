package com.example.dndcharacterapp.models.feature

data class FeatureItem(
    val `class`: Class,
    val description: List<String>,
    val featureSpecific: FeatureSpecific,
    val id: String,
    val index: String,
    val level: Int,
    val name: String,
    val parent: Parent,
    val prerequisiteFeatures: List<PrerequisiteFeature>,
    val subclass: Subclass
)