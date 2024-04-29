package com.example.dndcharacterapp.models.magicitem.api

data class MagicItem(
    val desc: List<String>,
    val equipmentCategory: EquipmentCategory,
    val id: String,
    val index: String,
    val name: String,
    val rarity: Rarity,
    val variant: Boolean,
    val variants: List<Variant>
)