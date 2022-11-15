apply {
    from("$rootDir/compose-module.gradle")
}

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    defaultConfig {
        buildConfigField("String","API_KEY", "\"0266c46c25aa2fd93373aba4f48e0fe8\"")
    }
}

dependencies {
    "implementation"(project(Modules.movieDomain))
}