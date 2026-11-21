package com.app.mailmanager.core.feature

/**
 * Central registry holding independent feature modules.
 * Currently registers only the Mail Manager feature.
 */
class FeatureRegistry {
    private val features = LinkedHashMap<String, AppFeature>()

    fun register(feature: AppFeature) {
        features[feature.id] = feature
    }

    fun getFeature(id: String): AppFeature? = features[id]

    fun getAllFeatures(): List<AppFeature> = features.values.toList()
}
