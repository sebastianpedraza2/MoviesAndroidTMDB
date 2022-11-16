apply {
    from("$rootDir/compose-module.gradle")
}

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    defaultConfig {
        buildConfigField("String","API_KEY", "\"59d84a3a4231ee9fb301ec9ed5d5b843\"")
    }
}

dependencies {
    "api"(project(Modules.moviePresentation))
    "api"(project(Modules.movieData))
    "api"(project(Modules.movieDomain))

    //Retrofit
    "implementation"(Retrofit.retrofit)

    //Room
    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}

tasks.register("runUnitTests") {
    dependsOn(
        ":android-helpers:test",
        ":app:test",
        ":core:test",
        ":kotlin-helpers:test",
        ":feature-movie:test",
        ":feature-movie:movie-data:test",
        ":feature-movie:movie-domain:test",
        ":feature-movie:movie-presentation:test",
    )
    group = "CI"
    description = "$ ./gradlew runUnitTests # runs on GitHub Action"
}