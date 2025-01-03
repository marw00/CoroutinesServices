package com.example.jetpackcompose.storage

import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * A singleton object that holds the keys used for storing and retrieving data
 * in the DataStore.
 */
object Keys {
    val HOMETOWN_KEY = stringPreferencesKey("hometown_key")
    val API_TOKEN_KEY = stringPreferencesKey("api_token_key")
    val TIMER_OPTION_KEY = stringPreferencesKey("timer_option_key")
}
