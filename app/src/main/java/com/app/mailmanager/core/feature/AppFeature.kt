package com.app.mailmanager.core.feature

import androidx.fragment.app.Fragment

/**
 * Contract for independent, self-contained app features.
 * Future features implement this interface and register in [FeatureRegistry]
 * without modifying existing feature modules.
 */
interface AppFeature {
    val id: String
    val title: String
    fun createFragment(): Fragment
}
