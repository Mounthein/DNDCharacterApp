package com.example.dndcharacterapp.api

import com.example.dndcharacterapp.models.abilityScore.AbilityScore
import com.example.dndcharacterapp.models.abilityScore.AbilityScores
import com.example.dndcharacterapp.models.alignment.Alignment
import com.example.dndcharacterapp.models.alignment.Alignments
import com.example.dndcharacterapp.models.background.Background
import com.example.dndcharacterapp.models.background.Backgrounds
import com.example.dndcharacterapp.models.classes.Classes
import com.example.dndcharacterapp.models.classes.ClassesItem
import com.example.dndcharacterapp.models.condition.Condition
import com.example.dndcharacterapp.models.condition.Conditions
import com.example.dndcharacterapp.models.damagetype.DamageType
import com.example.dndcharacterapp.models.damagetype.DamageTypes
import com.example.dndcharacterapp.models.equipment.Equipment
import com.example.dndcharacterapp.models.equipment.Equipments
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiDndService {
    @GET("/api/AbilityScore/")
    suspend fun getAbilityScoreList(): Response<AbilityScores>

    @GET("/api/AbilityScore/{id}/")
    suspend fun getAbilityScore(
        @Path("id") id: String
    ): Response<AbilityScore>

    // ========================================================== //
    // ========================================================== //
    @GET("/api/Alignment/")
    suspend fun getAlignmentList(): Response<Alignments>

    @GET("/api/Alignment/{id}/")
    suspend fun getAlignment(
        @Path("id") id: String
    ): Response<Alignment>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Background/")
    suspend fun getBackgroundList(): Response<Backgrounds>

    @GET("/api/Background/{id}/")
    suspend fun getBackground(
        @Path("id") id: String
    ): Response<Background>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Classes/")
    suspend fun getClassesList(): Response<Classes>

    @GET("/api/Classes/{id}/")
    suspend fun getClasses(
        @Path("id") id: String
    ): Response<ClassesItem>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Conditions/")
    suspend fun getConditionList(): Response<Conditions>

    @GET("/api/Conditions/{id}/")
    suspend fun getCondition(
        @Path("id") id: String
    ): Response<Condition>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/DamageType/")
    suspend fun getDamageTypeList(): Response<DamageTypes>

    @GET("/api/DamageType/{id}/")
    suspend fun getDamageType(
        @Path("id") id: String
    ): Response<DamageType>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Equipment/")
    suspend fun getEquipmentList(): Response<Equipments>

    @GET("/api/Equipment/{id}/")
    suspend fun getEquipment(
        @Path("id") id: String
    ): Response<Equipment>
}
