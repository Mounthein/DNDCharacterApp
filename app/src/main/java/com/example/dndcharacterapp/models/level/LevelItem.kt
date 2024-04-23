package com.example.dndcharacterapp.models.level

data class LevelItem(
    val abilityScoreBonuses: Int,
    val `class`: Class,
    val classLevels: Any,
    val classSpecific: ClassSpecific,
    val features: List<Feature>,
    val id: String,
    val index: String,
    val levelN: Int,
    val proficiencyBonus: Int,
    val spellcasting: Spellcasting,
    val subcalss: Subcalss,
    val subcategories: Subcategories
)