package com.example.dndcharacterapp.models.user

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.BsonObjectId

data class apiUser(
    var username: String,
    var password: String
)

