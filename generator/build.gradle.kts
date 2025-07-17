plugins {
    kotlin("jvm") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

repositories { mavenCentral() }

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-cli:0.3.6")          // <- add this
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.2")
    implementation("com.squareup.moshi:moshi:1.15.0")
}


application { mainClass.set("cloudintel.generator.MainKt") }
