plugins {
    kotlin("jvm") version "2.3.20"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.http4k:http4k-bom:6.37.0.0"))

    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-jetty")    
}

kotlin {
    jvmToolchain(25)
}

tasks.test {
    useJUnitPlatform()
}
