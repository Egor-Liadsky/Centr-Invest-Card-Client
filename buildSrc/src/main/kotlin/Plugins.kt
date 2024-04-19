object PluginVersion {
    const val gradle = "8.0"
    const val kotlin = "1.9.10"
    const val androidApp = "8.0.0"
}

object Plugins {
    val gradle by lazy { "com.android.tools.build:gradle:${PluginVersion.gradle}"}
}
