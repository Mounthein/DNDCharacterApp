package com.example.dndcharacterapp.models.spell

import androidx.compose.runtime.Immutable

@Immutable
data class Spell(
    val areaOfEffect: AreaOfEffect?,
    val attackType: String,
    val castingTime: String,
    val classes: List<Classe>,
    val components: List<String>,
    val concentration: Boolean,
    val damageSpell: DamageSpell?,
    val dc: Dc,
    val description: List<String>,
    val duration: String,
    val from: From,
    val healAtSlotLevel: HealAtSlotLevel?,
    val higherLevel: List<String>,
    val id: String,
    val index: String,
    val level: Int,
    val material: String,
    val name: String,
    val range: String,
    val ritual: Boolean,
    val subclasses: List<Subclasse>
)