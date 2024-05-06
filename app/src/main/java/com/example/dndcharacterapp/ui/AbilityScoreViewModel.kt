package com.example.dndcharacterapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dndcharacterapp.api.CrudApi
import com.example.dndcharacterapp.models.abilityScore.AbilityScore

// Esto es de testing, si da tiempo se puede intentar implementar, ignorar por el momento
class AbilityScoreViewModel : ViewModel(){

    val abilityScores = MutableLiveData<AbilityScore>()

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email
    fun RandomAbilityScore(){
        val currentAbilityScore: AbilityScore? = CrudApi().getAbilityScoreList()?.get((0..5).random())
        abilityScores.postValue(currentAbilityScore!!)
    }
}