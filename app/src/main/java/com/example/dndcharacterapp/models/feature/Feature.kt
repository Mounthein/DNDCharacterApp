package com.example.dndcharacterapp.models.feature

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.EmbeddedRealmObject
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject

class Feature : RealmObject{
    val `class`: Class? = null
    val description: RealmList<String>? = null
    val featureSpecific: FeatureSpecific? = null
    val id: String? = null
    val index: String? = null
    val level: Int? = null
    val name: String? = null
    val parent: Parent? = null
    val prerequisiteFeatures: RealmList<PrerequisiteFeature> = realmListOf()
    val subclass: Subclass? = null
}