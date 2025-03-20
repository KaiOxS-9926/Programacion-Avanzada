plugins {
    id("java")
    id("io.freefair.lombok") version "8.11"
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    // Contender CDI: Weld
    implementation("org.jboss.weld.se:weld-se-core:5.1.3.Final")
    implementation("io.smallrye:jandex:3.2.3")

    // Base de datos: SQLITE
    implementation("org.xerial:sqlite-jdbc:3.47.0.0")
    implementation("com.zaxxer:HikariCP:6.1.0")
}

sourceSets {
    main {
        output.setResourcesDir( file("${buildDir}/classes/java/main") )
    }
}