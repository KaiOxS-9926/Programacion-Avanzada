plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val springVersion = "6.2.0"

dependencies {
    implementation("org.springframework:spring-core:${springVersion}")
    implementation("org.springframework:spring-context:${springVersion}")

    // Base de datos
    implementation("org.xerial:sqlite-jdbc:3.47.0.0")
    implementation("com.zaxxer:HikariCP:6.1.0")

}
