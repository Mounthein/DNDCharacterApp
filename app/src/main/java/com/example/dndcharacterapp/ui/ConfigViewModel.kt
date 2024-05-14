package com.example.dndcharacterapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dndcharacterapp.models.user.User
import com.example.dndcharacterapp.realm.RealmApp
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ConfigViewModel: ViewModel() {
    private val realm = RealmApp.realm


    val user = realm
        .query<User>()
        .asFlow()
        .map { results ->
            results.list.toList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            emptyList()
        )

    public fun setLogedUser(user: User){
        viewModelScope.launch {
            realm.writeBlocking {
                copyToRealm(user, updatePolicy = UpdatePolicy.ALL)
            }
        }
    }
}