package com.app.mailmanager.features.mailmanager.data

import com.app.mailmanager.features.mailmanager.domain.MailRepository

class MailRepositoryImpl(
    private val localStorage: LocalMailStorage
) : MailRepository {

    override fun getSavedMail(): String? = localStorage.readMail()

    override fun saveMail(email: String) {
        localStorage.writeMail(email)
    }
}
