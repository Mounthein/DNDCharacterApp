package com.example.dndcharacterapp.api

import com.example.dndcharacterapp.models.abilityscore.AbilityScore
import com.example.dndcharacterapp.models.abilityscore.Skill
import com.example.dndcharacterapp.models.abilityscore.api.AbilityScores
import com.example.dndcharacterapp.models.alignment.Alignment
import com.example.dndcharacterapp.models.alignment.api.Alignement
import com.example.dndcharacterapp.realm.RealmApp
import com.google.gson.GsonBuilder
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.toRealmList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.example.dndcharacterapp.models.alignment.api.Alignements
import com.example.dndcharacterapp.models.background.Alignments
import com.example.dndcharacterapp.models.background.Background
import com.example.dndcharacterapp.models.background.Bonds
import com.example.dndcharacterapp.models.background.Equipment
import com.example.dndcharacterapp.models.background.EquipmentCategory
import com.example.dndcharacterapp.models.background.Feature
import com.example.dndcharacterapp.models.background.Flaws
import com.example.dndcharacterapp.models.background.From
import com.example.dndcharacterapp.models.background.FromX
import com.example.dndcharacterapp.models.background.Ideals
import com.example.dndcharacterapp.models.background.PersonalityTraits
import com.example.dndcharacterapp.models.background.StartingEquipment
import com.example.dndcharacterapp.models.background.StartingEquipmentOption
import com.example.dndcharacterapp.models.background.StartingProficiency
import com.example.dndcharacterapp.models.background.api.Backgrounds
import org.mongodb.kbson.BsonObjectId
import org.mongodb.kbson.ObjectId
import kotlin.coroutines.CoroutineContext
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response
import retrofit2.Retrofit



class CrudApi():CoroutineScope {
    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val urlapi = "https://lastmintboat26.conveyor.cloud/"

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
    fun getAbilityScoreList(): Boolean?{
        var resposta: Response<AbilityScores>? = null

        runBlocking {
            val corrutina = launch {
                var realm = RealmApp.realm
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getAbilityScoreList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            saveAbilityScores(resposta!!.body()!!)
            return true
        }else {
            return null
        }
    }

    fun getAbilityScore(id: String): com.example.dndcharacterapp.models.abilityscore.api.AbilityScore?{
        var resposta: Response<com.example.dndcharacterapp.models.abilityscore.api.AbilityScore>? = null

        runBlocking {
            val corrutina = launch {
                var realm = RealmApp.realm
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
    fun getAlignmentList(): Boolean?{
        var resposta: Response<Alignements>? = null

        runBlocking {
            val corrutina = launch {
                var realm = RealmApp.realm
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getAlignmentList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            saveAlignments(resposta!!.body()!!)
            return true
        }else {
            return null
        }
    }

    fun getAlignment(id: String): Alignement?{
        var resposta: Response<Alignement>? = null

        runBlocking {
            val corrutina = launch {
                var realm = RealmApp.realm
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

    fun getBackgroundList(): Boolean?{
        var resposta: Response<Backgrounds>? = null

        runBlocking {
            val corrutina = launch {
                var realm = RealmApp.realm
                resposta = getRetrofit().create(ApiDndService::class.java)
                    .getBackgroundList()
            }
            corrutina.join()
        }
        if (resposta!!.isSuccessful) {
            saveBackgrounds(resposta!!.body()!!)
            return true
        }else {
            return null
        }
    }

    fun getBackground(id: String): com.example.dndcharacterapp.models.background.api.Background?{
        var resposta: Response<com.example.dndcharacterapp.models.background.api.Background>? = null

        runBlocking {
            val corrutina = launch {
                var realm = RealmApp.realm
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
}


// ========================================================== //
// ========================================================== //


private fun saveAbilityScores(abilityScores: AbilityScores) {
    var realm = RealmApp.realm
    realm.writeBlocking {
        abilityScores.forEach { api ->
            //var ability = realm.query<AbilityScore>("_id == $0", ObjectId(api.id)).first()
            var abs: AbilityScore = AbilityScore()
            abs.id = ObjectId(api.id)
            abs.description = api.description.toRealmList()
            abs.fullName = api.fullName
            abs.index = api.index
            api.skills.forEach { sk ->
                var ski = Skill()
                ski.index = sk.index
                ski.name = sk.name
                abs.skills?.add(ski)
            }
            copyToRealm(abs, updatePolicy = UpdatePolicy.ALL)
        }
    }
}

private fun saveAlignments(alignments: Alignements) {
    var realm = RealmApp.realm
    realm.writeBlocking {
        alignments.forEach{ali ->
            var align: Alignment = Alignment()
            align.id = ObjectId(ali.id)
            align.abbreviation = ali.abbreviation
            align.description = ali.description
            align.index = ali.index
            align.name = ali.name
            copyToRealm(align, updatePolicy = UpdatePolicy.ALL)
        }
    }
}

private fun saveBackgrounds(backgrounds: Backgrounds) {
    var realm = RealmApp.realm
    realm.writeBlocking {
        backgrounds.forEach { back ->
            var ba: Background = Background()
            ba.id = ObjectId(back.id)
            ba.index = back.index
            ba.languageOptions = back.languageOptions
            ba.name = back.name

            // Objecte Bond
            var bond: Bonds = Bonds()
            bond.choose = back.bonds.choose
            bond.from = back.bonds.from.toRealmList()
            ba.bonds = bond

            // Objecte Feature
            var feat: Feature = Feature()
            feat.name = back.feature.name
            feat.description = back.feature.description.toRealmList()

            // Object flaws
            var flaw: Flaws = Flaws()
            flaw.choose = back.flaws.choose
            flaw.from = back.flaws.from.toRealmList()
            ba.flaws = flaw

            // Object ideals
            var ideal: Ideals = Ideals()
            ideal.choose = back.ideals.choose
            back.ideals.from.forEach {f ->
                var fr = From()
                var ali = Alignments()
                ali.index = f.alignments.index.toRealmList()
                ali.name = f.alignments.name.toRealmList()
                fr.alignments = ali
                fr.description = f.description
            }
            ideal.type = back.ideals.type
            ba.ideals = ideal

            // Object personality Traits
            var personalityTraits: PersonalityTraits = PersonalityTraits()
            personalityTraits.choose = back.personalityTraits.choose
            personalityTraits.from = back.personalityTraits.from.toRealmList()
            ba.personalityTraits = personalityTraits

            // Object startingEquipment
            back.startingEquipment.forEach { se ->
                var startingEquipment: StartingEquipment = StartingEquipment()
                var equipment: Equipment = Equipment()
                equipment.index = se.equipment.index
                equipment.name = se.equipment.name
                startingEquipment.equipment = equipment
                startingEquipment.quantity = se.quantity
                ba.startingEquipment.add(startingEquipment)
            }

            // Object startingEquipmentOption
            back.startingEquipmentOption.forEach {seo ->
                var startingeo: StartingEquipmentOption = StartingEquipmentOption()
                var fromX: FromX = FromX()
                var equipmentCategory: EquipmentCategory = EquipmentCategory()
                equipmentCategory.index = seo.from.equipmentCategory.index
                equipmentCategory.name = seo.from.equipmentCategory.name
                fromX.equipmentCategory = equipmentCategory

                startingeo.from = fromX
                startingeo.choose = seo.choose
                startingeo.type = seo.type

                ba.startingEquipmentOption.add(startingeo)
            }

            // Object Starting Proficiencies
            back.startingProficiencies.forEach { sp ->
                var startingProficiency: StartingProficiency = StartingProficiency()
                startingProficiency.index = sp.index
                startingProficiency.name = sp.name
                ba.startingProficiencies.add(startingProficiency)
            }

            copyToRealm(ba, updatePolicy = UpdatePolicy.ALL)
        }
    }
}