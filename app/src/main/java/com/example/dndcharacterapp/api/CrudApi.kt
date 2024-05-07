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
import com.example.dndcharacterapp.models.weaponproperty.WeaponProperties
import com.example.dndcharacterapp.models.weaponproperty.WeaponProperty
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext


class CrudApi():CoroutineScope {
    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val urlapi = "https://smallyellowshed89.conveyor.cloud/"

    private fun getClient(): OkHttpClient {
        var login = HttpLoggingInterceptor()
        login.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(login).build()
    }

    private fun getRetrofit(): Retrofit {
        val gson = GsonBuilder().setLenient().create()

        return Retrofit.Builder().baseUrl(urlapi).client(getClient()).addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    // ========================================================== //
    // ========================================================== //

    fun getAbilityScoreList(): AbilityScores?{
        var resposta: Response<AbilityScores>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getAbilityScoreList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getAbilityScore(id: String): AbilityScore?{
        var resposta: Response<AbilityScore>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getAbilityScore(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getAlignmentList(): Alignments?{
        var resposta: Response<Alignments>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getAlignmentList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getAlignment(id: String): Alignment?{
        var resposta: Response<Alignment>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getAlignment(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getBackgroundList(): Backgrounds?{
        var resposta: Response<Backgrounds>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getBackgroundList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getBackground(id: String): Background?{
        var resposta: Response<Background>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getBackground(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getClassesList(): Classes?{
        var resposta: Response<Classes>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getClassesList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getClasses(id: String): ClassesItem?{
        var resposta: Response<ClassesItem>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getClasses(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getConditionList(): Conditions?{
        var resposta: Response<Conditions>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getConditionList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getCondition(id: String): Condition?{
        var resposta: Response<Condition>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getCondition(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getDamageTypeList(): DamageTypes?{
        var resposta: Response<DamageTypes>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getDamageTypeList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getDamageType(id: String): DamageType?{
        var resposta: Response<DamageType>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getDamageType(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getEquipmentList(): Equipments?{
        var resposta: Response<Equipments>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getEquipmentList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getEquipment(id: String): Equipment?{
        var resposta: Response<Equipment>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getEquipment(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getEquipmentCategoryList(): EquipmentCategories?{
        var resposta: Response<EquipmentCategories>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getEquipmentCategoryList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getEquipmentCategory(id: String): EquipmentCategory?{
        var resposta: Response<EquipmentCategory>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getEquipmentCategory(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getFeatList(): Feats?{
        var resposta: Response<Feats>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getFeatList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getFeat(id: String): Feat?{
        var resposta: Response<Feat>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getFeat(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getFeatureList(): Features?{
        var resposta: Response<Features>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getFeatureList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getFeature(id: String): Feature?{
        var resposta: Response<Feature>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getFeature(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getLanguageList(): Languages?{
        var resposta: Response<Languages>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getLanguageList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getLanguage(id: String): Language?{
        var resposta: Response<Language>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getLanguage(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getLevelList(): Levels?{
        var resposta: Response<Levels>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getLevelList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getLevel(id: String): Level?{
        var resposta: Response<Level>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getLevel(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getMagicItemList(): MagicItems?{
        var resposta: Response<MagicItems>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getMagicItemList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getMagicItem(id: String): MagicItem?{
        var resposta: Response<MagicItem>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getMagicItem(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getMagicSchoolList(): MagicSchools?{
        var resposta: Response<MagicSchools>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getMagicSchoolList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getMagicSchool(id: String): MagicSchool?{
        var resposta: Response<MagicSchool>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getMagicSchool(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getProficiencyList(): Proficiencies?{
        var resposta: Response<Proficiencies>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getProficiencyList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getProficiency(id: String): Proficiency?{
        var resposta: Response<Proficiency>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getProficiency(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getRaceList(): Races?{
        var resposta: Response<Races>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getRaceList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getRace(id: String): Race?{
        var resposta: Response<Race>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getRace(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getSkillList(): Skills?{
        var resposta: Response<Skills>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getSkillList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getSkill(id: String): Skill?{
        var resposta: Response<Skill>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getSkill(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getSpellList(): Spells?{
        var resposta: Response<Spells>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getSpellList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getSpell(id: String): Spell?{
        var resposta: Response<Spell>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getSpell(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getSubclassList(): Subclasses?{
        var resposta: Response<Subclasses>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getSubclassList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getSubclass(id: String): Subclass?{
        var resposta: Response<Subclass>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getSubclass(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getSubraceList(): Subraces?{
        var resposta: Response<Subraces>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getSubraceList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getSubrace(id: String): Subrace?{
        var resposta: Response<Subrace>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getSubrace(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getTraitList(): Traits?{
        var resposta: Response<Traits>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getTraitList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getTrait(id: String): Trait?{
        var resposta: Response<Trait>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getTrait(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    // ========================================================== //
    // ========================================================== //


    fun getWeaponPropertyList(): WeaponProperties?{
        var resposta: Response<WeaponProperties>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getWeaponPropertyList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

    fun getWeaponProperty(id: String): WeaponProperty?{
        var resposta: Response<WeaponProperty>? = null

        runBlocking {
            val corrutina = launch {
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getWeaponProperty(id)
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            return resposta!!.body()!!
        }else {
            return null
        }
    }

}