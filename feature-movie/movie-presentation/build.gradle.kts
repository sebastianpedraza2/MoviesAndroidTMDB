apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.movieDomain))
    "implementation"(project(Modules.androidHelpers))

    //Coil
    "implementation"(Ui.coilCompose)

    //Lottie
    "implementation"(Ui.lottie)
}