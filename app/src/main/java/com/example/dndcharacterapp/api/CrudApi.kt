package com.example.dndcharacterapp.api

import com.example.dndcharacterapp.models.abilityscore.AbilityScore
import com.example.dndcharacterapp.models.abilityscore.Skill
import com.example.dndcharacterapp.models.abilityscore.api.AbilityScores
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

    fun getAbilityScoreList(id: String): com.example.dndcharacterapp.models.abilityscore.api.AbilityScore?{
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
}

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
