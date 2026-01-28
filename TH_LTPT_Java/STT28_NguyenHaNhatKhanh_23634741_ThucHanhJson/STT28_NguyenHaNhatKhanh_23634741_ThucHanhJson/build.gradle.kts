plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Source: https://mvnrepository.com/artifact/org.eclipse.parsson/parsson
    implementation("org.eclipse.parsson:parsson:1.1.7")
    // Source: https://mvnrepository.com/artifact/jakarta.json/jakarta.json-api
    implementation("jakarta.json:jakarta.json-api:2.1.3")
    // Source: https://mvnrepository.com/artifact/org.projectlombok/lombok
    implementation("org.projectlombok:lombok:1.18.42")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}