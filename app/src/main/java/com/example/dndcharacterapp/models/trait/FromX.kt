package com.example.dndcharacterapp.models.trait

data class FromX(
    val choice: SubtraitOptions,
    val index: String,
    val items: Any,
    val name: String
)

data class Items(
    val choice: ItemsChoiceFeature,
    val item: List<ArrayFrom>
)

data class ItemsChoiceFeature(
    val choose: List<Int>,
    val from: List<ArrayFrom>
)

data class ArrayFrom(
    val index: List<String>,
    val name: List<String>
)