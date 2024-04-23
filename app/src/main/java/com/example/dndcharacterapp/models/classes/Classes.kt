package com.example.dndcharacterapp.models.classes

data class Classes(
    val hitDie: Int,
    val id: String,
    val index: String,
    val multiClassing: MultiClassing,
    val name: String,
    val proficiencies: List<ProficiencyX>,
    val proficienciesChoices: List<ProficienciesChoice>,
    val savingThrows: List<SavingThrow>,
    val spellcasting: Spellcasting,
    val startingEquipment: List<StartingEquipment>,
    val startingEquipmentOption: List<StartingEquipmentOption>,
    val subclasses: List<Subclasse>
)