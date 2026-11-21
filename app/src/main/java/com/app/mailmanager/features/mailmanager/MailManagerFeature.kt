package com.app.mailmanager.features.mailmanager

import androidx.fragment.app.Fragment
import com.app.mailmanager.core.feature.AppFeature
import com.app.mailmanager.features.mailmanager.ui.MailHomeFragment

class MailManagerFeature : AppFeature {
    override val id: String = FEATURE_ID
    override val title: String = "Mail Manager"

    override fun createFragment(): Fragment = MailHomeFragment()

    companion object {
        const val FEATURE_ID = "feature_mail_manager"
    }
}
