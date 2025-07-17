plugins {
    kotlin("jvm") version "2.0.0"   // use the latest stable
    application
}

repositories { mavenCentral() }

dependencies {
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.2")   // fast CSV writer
    implementation("com.squareup.moshi:moshi:1.15.0")               // JSON if you want it
    testImplementation(kotlin("test"))
}

application { mainClass.set("cloudintel.generator.MainKt") }

tasks.test { useJUnitPlatform() }
