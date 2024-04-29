package com.example.dndcharacterapp.realm

import android.app.Application
import com.example.dndcharacterapp.models.abilityscore.AbilityScore
import com.example.dndcharacterapp.models.abilityscore.Skill
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration


class RealmApp: Application() {

    companion object {
        lateinit var realm:Realm
    }

    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    AbilityScore::class,
                    Skill::class,
                )
            )
        )
    }
}