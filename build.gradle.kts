plugins {
  application
  kotlin("jvm") version "1.5.0"
}

application {
  mainClass.set("com.sacredit.BootstrapKt")
}

group = "com.creditas"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  testImplementation("org.assertj:assertj-core:3.12.2")
  testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
  testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.2")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

tasks {
  test {
    useJUnitPlatform()
  }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
  }
}

task("storeBootstrap", JavaExec::class) {
  standardInput = System.`in`
  main = "com.sacredit.StoreBootstrapKt"
  classpath = sourceSets["main"].runtimeClasspath
}