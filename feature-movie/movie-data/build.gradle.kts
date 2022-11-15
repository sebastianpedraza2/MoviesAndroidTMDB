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

    //Modules
    "implementation"(project(Modules.movieDomain))

    //Retrofit
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.gsonConverter)
    "implementation"(Retrofit.gson)

    //Room
    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}