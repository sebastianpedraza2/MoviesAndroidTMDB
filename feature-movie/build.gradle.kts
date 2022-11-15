apply {
    from("$rootDir/compose-module.gradle")
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