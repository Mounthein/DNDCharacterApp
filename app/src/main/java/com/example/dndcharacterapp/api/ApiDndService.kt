package com.example.dndcharacterapp.api
import com.example.dndcharacterapp.models.abilityscore.api.AbilityScore
import com.example.dndcharacterapp.models.abilityscore.api.AbilityScores
import com.example.dndcharacterapp.models.alignment.api.Alignement
import com.example.dndcharacterapp.models.alignment.api.Alignements
import com.example.dndcharacterapp.models.background.api.Background
import com.example.dndcharacterapp.models.background.api.Backgrounds
import com.example.dndcharacterapp.models.classes.api.Class
import com.example.dndcharacterapp.models.classes.api.Classes
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
    suspend fun getAlignmentList(): Response<Alignements>

    @GET("/api/Alignment/{id}/")
    suspend fun getAlignment(
        @Path("id") id: String
    ): Response<Alignement>

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
    ): Response<Class>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Skill/")
    suspend fun getSkillList(): Response<Classes>


}
