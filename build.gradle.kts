val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val logbackEncoderVersion: String by project
val ktormVersion: String by project
val libPhoneNumberVersion: String by project
val libPhoneNumberGeocoderVersion: String by project
val libPhoneNumberPrefixMapperVersion: String by project
val libPhoneNumberCarrierVersion: String by project
val hikariVersion: String by project
val postgresVersion: String by project
val awsKotlinVersion: String by project
val bucket4jVersion: String by project
val castleVersion: String by project
val jbcryptVersion: String by project
val jacksonKotlinVersion: String by project
val twilioVersion: String by project
val livekitVersion: String by project
val googleCloudBomVersion: String by project

group = "com.maikoudi"
version = "0.0.1"

plugins {
    kotlin("jvm") version "2.1.0-Beta1"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.0-Beta1"
    id("io.ktor.plugin") version "3.0.1"
    id("maven-publish")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation("io.ktor:ktor-serialization-kotlinx-json")

    implementation("io.ktor:ktor-client-content-negotiation")
    implementation("io.ktor:ktor-client-cio")
    implementation("io.ktor:ktor-client-logging")
    implementation("io.ktor:ktor-client-auth")
    implementation("io.ktor:ktor-client-websockets")

    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-server-call-logging")
    implementation("io.ktor:ktor-server-sessions")
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-freemarker")
    implementation("io.ktor:ktor-server-host-common")
    implementation("io.ktor:ktor-server-cors")
    implementation("io.ktor:ktor-server-status-pages")
    implementation("io.ktor:ktor-server-rate-limit")
    implementation("io.ktor:ktor-server-double-receive")
    implementation("io.ktor:ktor-server-auth")
    implementation("io.ktor:ktor-server-auth-jwt")
    implementation("io.ktor:ktor-server-call-id")
    implementation("io.ktor:ktor-server-websockets")
    implementation("io.ktor:ktor-server-forwarded-header")
    implementation("io.ktor:ktor-server-metrics")
    implementation("io.ktor:ktor-server-metrics-micrometer")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logbackEncoderVersion")

    implementation("org.ktorm:ktorm-core:$ktormVersion")
    implementation("org.ktorm:ktorm-support-postgresql:$ktormVersion")
    implementation("com.zaxxer:HikariCP:$hikariVersion")
    implementation("org.postgresql:postgresql:$postgresVersion")
    implementation("io.castle:castle-java:$castleVersion")
    implementation("org.mindrot:jbcrypt:$jbcryptVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonKotlinVersion")

    implementation("com.googlecode.libphonenumber:libphonenumber:$libPhoneNumberVersion")
    implementation("com.googlecode.libphonenumber:geocoder:$libPhoneNumberGeocoderVersion")
    implementation("com.googlecode.libphonenumber:prefixmapper:$libPhoneNumberPrefixMapperVersion")
    implementation("com.googlecode.libphonenumber:carrier:$libPhoneNumberCarrierVersion")

    implementation(platform("com.google.cloud:libraries-bom:$googleCloudBomVersion"))
    implementation("com.google.cloud:google-cloud-recaptchaenterprise")
    implementation("com.google.cloud:recaptcha-password-check-helpers:1.0.4")
    implementation("com.google.apis:google-api-services-playintegrity:v1-rev20240813-2.0.0")
    implementation("com.google.firebase:firebase-admin:9.4.1")

    implementation(awssdk.services.s3)
    implementation(awssdk.services.ses)
    implementation(awssdk.services.pinpoint)
    implementation(awssdk.services.sns)
    implementation(awssdk.services.chimesdkmeetings)
    implementation(awssdk.services.chimesdkmediapipelines)
    implementation(awssdk.services.wafv2)

    implementation("com.bucket4j:bucket4j_jdk17-core:$bucket4jVersion")
    implementation("com.bucket4j:bucket4j_jdk17-jcache:$bucket4jVersion")

    implementation("com.github.jwt-scala:jwt-play-json_3:10.0.1")
    implementation("io.livekit:livekit-server:$livekitVersion")
    implementation("com.twilio.sdk:twilio:$twilioVersion")

    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

repositories {
    mavenCentral()

    maven {
        url = uri("https://esdiac-584252333092.d.codeartifact.us-west-2.amazonaws.com/maven/esdiacapp/")
        credentials {
            username = "aws"
            password = System.getenv("CODEARTIFACT_AUTH_TOKEN")
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.withType<Zip> {
    isZip64 = true
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}