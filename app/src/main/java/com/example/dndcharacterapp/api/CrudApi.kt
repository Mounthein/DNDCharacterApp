package com.example.dndcharacterapp.api

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
    /*
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
            //saveAbilityScores(resposta!!.body()!!)
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
     */
    // ========================================================== //
    // ========================================================== //

}