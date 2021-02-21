plugins {
  kotlin("multiplatform").version("1.4.30")
  id("maven-publish")
}

repositories {
  mavenCentral()
}

kotlin {
  jvm()
  macosX64()
  mingwX64()
}

group = "com.example"
version = "0.1"
publishing {
  repositories {
    maven {
      name = "Oss"
      setUrl {
        val repositoryId =
          System.getenv("SONATYPE_REPOSITORY_ID") ?: error("Missing env variable: SONATYPE_REPOSITORY_ID")
        "https://oss.sonatype.org/service/local/staging/deployByRepositoryId/${repositoryId}/"
      }
      credentials {
        username = System.getenv("SONATYPE_USERNAME")
        password = System.getenv("SONATYPE_PASSWORD")
      }
    }
  }
  publications {
    withType<MavenPublication> {
      pom {
        name.set("create-nexus-staging-repo-sample")
        description.set("a sample project for the create-nexus-staging-repo")
        url.set("https://github.com/martinbonnin/create-nexus-staging-repo-sample/")
        developers {
          developer {
            name.set("Martin Bonnin")
          }
        }
        licenses {
          license {
            name.set("MIT license")
            url.set("https://github.com/martinbonnin/create-nexus-staging-repo-sample/")
          }
        }
        scm {
          url.set("https://github.com/martinbonnin/create-nexus-staging-repo-sample/")
        }
      }
    }
  }
}