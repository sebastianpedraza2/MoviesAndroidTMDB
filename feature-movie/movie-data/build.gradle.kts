apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {

    //Modules
    "implementation"(project(Modules.movieDomain))
    "implementation"(project(Modules.core))

    //Retrofit
    "implementation"(Retrofit.retrofit)
    "implementation"(Retrofit.gsonConverter)
    "implementation"(Retrofit.gson)

    //Room
    "kapt"(Room.roomCompiler)
    "implementation"(Room.roomKtx)
    "implementation"(Room.roomRuntime)
}