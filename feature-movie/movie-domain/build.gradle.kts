apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "api"(project(Modules.kotlinHelpers))
    "api"(project(Modules.core))
    "implementation"(Coroutines.coroutinesCore)
}