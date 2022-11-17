apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {

    //Coil
    "implementation"(Ui.coilCompose)

    //Lottie
    "implementation"(Ui.lottie)

}
