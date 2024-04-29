package com.example.dndcharacterapp.models.equipmentcategory.api

data class EquipmentCategory(
    val equipment: List<Equipment>,
    val id: String,
    val index: String,
    val name: String
)