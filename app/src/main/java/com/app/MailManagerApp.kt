package com.app.mailmanager

import android.app.Application
import com.app.mailmanager.core.feature.FeatureRegistry
import com.app.mailmanager.features.mailmanager.MailManagerFeature
import com.app.mailmanager.features.mailmanager.data.LocalMailStorage
import com.app.mailmanager.features.mailmanager.data.MailRepositoryImpl
import com.app.mailmanager.features.mailmanager.domain.MailRepository

class MailManagerApp : Application() {

    lateinit var featureRegistry: FeatureRegistry
        private set

    lateinit var mailRepository: MailRepository
        private set

    override fun onCreate() {
        super.onCreate()

        // Initialize local storage & repository for Mail Manager
        val localStorage = LocalMailStorage(this)
        mailRepository = MailRepositoryImpl(localStorage)

        // Register independent features
        featureRegistry = FeatureRegistry().apply {
            register(MailManagerFeature())
        }
    }
}
