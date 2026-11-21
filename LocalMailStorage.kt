package com.app.mailmanager.features.mailmanager.data

import android.content.Context

class LocalMailStorage(context: Context) {

    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun readMail(): String? {
        return prefs.getString(KEY_GMAIL_ADDRESS, null)?.takeIf { it.isNotBlank() }
    }

    fun writeMail(email: String) {
        prefs.edit().putString(KEY_GMAIL_ADDRESS, email.trim()).apply()
    }

    companion object {
        private const val PREFS_NAME = "mail_manager_local_prefs"
        private const val KEY_GMAIL_ADDRESS = "saved_gmail_address"
    }
}
