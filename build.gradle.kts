plugins {
	kotlin("jvm") version "2.1.0"
	kotlin("plugin.spring") version "2.1.0"
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.mercadolivro"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.xerial:sqlite-jdbc:3.43.2.1")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.5")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.hibernate.orm:hibernate-community-dialects:6.4.0.Final")
	implementation ("org.hibernate.validator:hibernate-validator:8.0.1.Final")
	implementation ("jakarta.el:jakarta.el-api:5.0.1")
	implementation ("io.github.nefilim.kjwt:kjwt-core:1.0.0")
	implementation("io.arrow-kt:arrow-core-jvm:2.0.1")
	implementation("io.arrow-kt:arrow-annotations-jvm:2.0.1")
	implementation("io.arrow-kt:arrow-atomic-jvm:2.0.1")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
