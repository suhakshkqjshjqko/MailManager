package com.app.mailmanager.features.mailmanager.domain

interface MailRepository {
    fun getSavedMail(): String?
    fun saveMail(email: String)
}
