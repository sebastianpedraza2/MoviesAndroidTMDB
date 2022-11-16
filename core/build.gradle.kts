apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.androidHelpers))
//    "implementation"(project(Modules.featureMovie))
}