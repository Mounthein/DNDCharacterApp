package com.example.dndcharacterapp.models.feature.api

import com.example.dndcharacterapp.models.feature.FromX

data class ChoiceX(
    val choose: List<Int>,
    val item: List<FromX>
)