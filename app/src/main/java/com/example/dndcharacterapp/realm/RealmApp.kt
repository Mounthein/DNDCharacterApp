package com.example.dndcharacterapp.realm

import android.app.Application
import com.example.dndcharacterapp.models.characterRealm.CharacterRealm
import com.example.dndcharacterapp.models.characterRealm.EmAlignmentCh
import com.example.dndcharacterapp.models.characterRealm.EmArmorClassCh
import com.example.dndcharacterapp.models.characterRealm.EmClassItemCh
import com.example.dndcharacterapp.models.characterRealm.EmCoinCh
import com.example.dndcharacterapp.models.characterRealm.EmDeathSavesCh
import com.example.dndcharacterapp.models.characterRealm.EmEquipmentCh
import com.example.dndcharacterapp.models.characterRealm.EmFeatureCh
import com.example.dndcharacterapp.models.characterRealm.EmHitDieCh
import com.example.dndcharacterapp.models.characterRealm.EmHitPointsCh
import com.example.dndcharacterapp.models.characterRealm.EmLanguageCh
import com.example.dndcharacterapp.models.characterRealm.EmProficiencyCh
import com.example.dndcharacterapp.models.characterRealm.EmRaceCh
import com.example.dndcharacterapp.models.characterRealm.EmSkillProficiencyCh
import com.example.dndcharacterapp.models.characterRealm.EmSpellAbilityCh
import com.example.dndcharacterapp.models.characterRealm.EmSpellCh
import com.example.dndcharacterapp.models.characterRealm.EmStatCh
import com.example.dndcharacterapp.models.characterRealm.EmTraitCh
import com.example.dndcharacterapp.models.user.User
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
                    User::class,
                    CharacterRealm::class,
                    EmAlignmentCh::class,
                    EmArmorClassCh::class,
                    EmClassItemCh::class,
                    EmCoinCh::class,
                    EmDeathSavesCh::class,
                    EmEquipmentCh::class,
                    EmFeatureCh::class,
                    EmHitDieCh::class,
                    EmHitPointsCh::class,
                    EmLanguageCh::class,
                    EmProficiencyCh::class,
                    EmRaceCh::class,
                    EmSkillProficiencyCh::class,
                    EmSpellAbilityCh::class,
                    EmSpellCh::class,
                    EmStatCh::class,
                    EmStatCh::class,
                    EmTraitCh::class,
                    )
            )
        )
    }
}