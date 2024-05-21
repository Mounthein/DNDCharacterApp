package com.example.dndcharacterapp.api

import com.example.dndcharacterapp.models.abilityScore.AbilityScore
import com.example.dndcharacterapp.models.abilityScore.AbilityScores
import com.example.dndcharacterapp.models.alignment.Alignment
import com.example.dndcharacterapp.models.alignment.Alignments
import com.example.dndcharacterapp.models.background.Background
import com.example.dndcharacterapp.models.background.Backgrounds
import com.example.dndcharacterapp.models.character.Character
import com.example.dndcharacterapp.models.character.Characters
import com.example.dndcharacterapp.models.classes.Classes
import com.example.dndcharacterapp.models.classes.ClassesItem
import com.example.dndcharacterapp.models.condition.Condition
import com.example.dndcharacterapp.models.condition.Conditions
import com.example.dndcharacterapp.models.damagetype.DamageType
import com.example.dndcharacterapp.models.damagetype.DamageTypes
import com.example.dndcharacterapp.models.equipment.Equipment
import com.example.dndcharacterapp.models.equipment.Equipments
import com.example.dndcharacterapp.models.equipmentcategory.EquipmentCategories
import com.example.dndcharacterapp.models.equipmentcategory.EquipmentCategory
import com.example.dndcharacterapp.models.feat.Feat
import com.example.dndcharacterapp.models.feat.Feats
import com.example.dndcharacterapp.models.feature.Feature
import com.example.dndcharacterapp.models.feature.Features
import com.example.dndcharacterapp.models.language.Language
import com.example.dndcharacterapp.models.language.Languages
import com.example.dndcharacterapp.models.level.Level
import com.example.dndcharacterapp.models.level.Levels
import com.example.dndcharacterapp.models.magicitem.MagicItem
import com.example.dndcharacterapp.models.magicitem.MagicItems
import com.example.dndcharacterapp.models.magicschool.MagicSchool
import com.example.dndcharacterapp.models.magicschool.MagicSchools
import com.example.dndcharacterapp.models.proficiency.Proficiencies
import com.example.dndcharacterapp.models.proficiency.Proficiency
import com.example.dndcharacterapp.models.race.Race
import com.example.dndcharacterapp.models.race.Races
import com.example.dndcharacterapp.models.skill.Skill
import com.example.dndcharacterapp.models.skill.Skills
import com.example.dndcharacterapp.models.spell.Spell
import com.example.dndcharacterapp.models.spell.Spells
import com.example.dndcharacterapp.models.subclass.Subclass
import com.example.dndcharacterapp.models.subclass.Subclasses
import com.example.dndcharacterapp.models.subrace.Subrace
import com.example.dndcharacterapp.models.subrace.Subraces
import com.example.dndcharacterapp.models.trait.Trait
import com.example.dndcharacterapp.models.trait.Traits
import com.example.dndcharacterapp.models.user.apiUser
import com.example.dndcharacterapp.models.weaponproperty.WeaponProperties
import com.example.dndcharacterapp.models.weaponproperty.WeaponProperty
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import com.example.dndcharacterapp.models.character.Message as characterMessage
import com.example.dndcharacterapp.models.user.Message as userMessage

interface ApiDndService {

    @GET("/api/User/{id}/{pass}")
    suspend fun getUserOk(
        @Path("id") id: String, @Path("pass") pass: String
    ): Response<userMessage>


    @POST("/api/User/")
    suspend fun postUserOk(
        @Body user: apiUser
    ): Response<userMessage>

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

    // ========================================================== //
    // ========================================================== //

    @GET("/api/EquipmentCategory/")
    suspend fun getEquipmentCategoryList(): Response<EquipmentCategories>

    @GET("/api/EquipmentCategory/{id}/")
    suspend fun getEquipmentCategory(
        @Path("id") id: String
    ): Response<EquipmentCategory>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Feat/")
    suspend fun getFeatList(): Response<Feats>

    @GET("/api/Feat/{id}/")
    suspend fun getFeat(
        @Path("id") id: String
    ): Response<Feat>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Feature/")
    suspend fun getFeatureList(): Response<Features>

    @GET("/api/Feature/{id}/")
    suspend fun getFeature(
        @Path("id") id: String
    ): Response<Feature>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Language/")
    suspend fun getLanguageList(): Response<Languages>

    @GET("/api/Language/{id}/")
    suspend fun getLanguage(
        @Path("id") id: String
    ): Response<Language>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Level/")
    suspend fun getLevelList(): Response<Levels>

    @GET("/api/Level/{id}/")
    suspend fun getLevel(
        @Path("id") id: String
    ): Response<Level>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/MagicItem/")
    suspend fun getMagicItemList(): Response<MagicItems>

    @GET("/api/MagicItem/{id}/")
    suspend fun getMagicItem(
        @Path("id") id: String
    ): Response<MagicItem>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/MagicSchool/")
    suspend fun getMagicSchoolList(): Response<MagicSchools>

    @GET("/api/MagicSchool/{id}/")
    suspend fun getMagicSchool(
        @Path("id") id: String
    ): Response<MagicSchool>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Proficiency/")
    suspend fun getProficiencyList(): Response<Proficiencies>

    @GET("/api/Proficiency/{id}/")
    suspend fun getProficiency(
        @Path("id") id: String
    ): Response<Proficiency>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Race/")
    suspend fun getRaceList(): Response<Races>

    @GET("/api/Race/{id}/")
    suspend fun getRace(
        @Path("id") id: String
    ): Response<Race>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Skill/")
    suspend fun getSkillList(): Response<Skills>

    @GET("/api/Skill/{id}/")
    suspend fun getSkill(
        @Path("id") id: String
    ): Response<Skill>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Spell/")
    suspend fun getSpellList(): Response<Spells>

    @GET("/api/Spell/{id}/")
    suspend fun getSpell(
        @Path("id") id: String
    ): Response<Spell>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Subclass/")
    suspend fun getSubclassList(): Response<Subclasses>

    @GET("/api/Subclass/{id}/")
    suspend fun getSubclass(
        @Path("id") id: String
    ): Response<Subclass>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Subrace/")
    suspend fun getSubraceList(): Response<Subraces>

    @GET("/api/Subrace/{id}/")
    suspend fun getSubrace(
        @Path("id") id: String
    ): Response<Subrace>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Trait/")
    suspend fun getTraitList(): Response<Traits>

    @GET("/api/Trait/{id}/")
    suspend fun getTrait(
        @Path("id") id: String
    ): Response<Trait>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/WeaponProperty/")
    suspend fun getWeaponPropertyList(): Response<WeaponProperties>

    @GET("/api/WeaponProperty/{id}/")
    suspend fun getWeaponProperty(
        @Path("id") id: String
    ): Response<WeaponProperty>

    // ========================================================== //
    // ========================================================== //

    @GET("/api/Character/")
    suspend fun getCharacterList(): Response<Characters>

    @GET("/api/Character/{id}/")
    suspend fun getCharacterById(
        @Path("id") id: String
    ): Response<Character>

    @GET("/api/Character/{username}/")
    suspend fun getCharacterByUserName(
        @Path("username") username: String
    ): Response<Characters>

    @POST("/api/Character/")
    suspend fun postCharacter(
        @Body character: Character
    ): Response<characterMessage>
}
