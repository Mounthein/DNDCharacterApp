package com.example.dndcharacterapp.api

import com.example.dndcharacterapp.models.abilityScore.AbilityScore
import com.example.dndcharacterapp.models.abilityScore.AbilityScores
import com.example.dndcharacterapp.models.alignment.Alignment
import com.example.dndcharacterapp.models.alignment.Alignments
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

    private val urlapi = "https://bigredwave79.conveyor.cloud/"

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
                var realm = RealmApp.realm
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


    fun getAlignmentList(): Alignments?{
        var resposta: Response<Alignments>? = null

        runBlocking {
            val corrutina = launch {
                var realm = RealmApp.realm
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



}