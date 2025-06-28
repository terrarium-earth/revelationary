plugins {
    id("earth.terrarium.cloche") version "0.10.14"
    `maven-publish`
}

repositories {
    cloche.librariesMinecraft()

    mavenCentral()

    cloche {
        mavenNeoforgedMeta()
        mavenNeoforged()
    }

    maven(url = "https://maven.shedaniel.me/")
    maven(url = "https://api.modrinth.com/maven")
    maven(url = "https://maven2.bai.lol")
}

cloche {
    minecraftVersion = "1.21.1"

    metadata {
        modId = "revelationary"

        name = "Revelationary (Neoforge Port)"

        description = "Data Driven Block and Item Revelation system. Discover as you go!"

        license = "lGPL3"

        author("Terrarium")
        author("DaFuqs")

        url = "https://github.com/terrarium-earth"
        sources = "https://github.com/terrarium-earth/revelationary"
        issues = "https://github.com/terrarium-earth/revelationary/issues"

        icon = "assets/revelationary/icon.png"
    }

    singleTarget {
        neoforge {
            loaderVersion = "21.1.172"

            mixins.from(
                "src/main/revelationary.mixins.json",
                "src/main/revelationary.client.mixins.json",
            )

            @Suppress("UnstableApiUsage")
            dependencies {
                modCompileOnly(module(group = "maven.modrinth", name = "jade", version = "15.10.0+neoforge"))
                modCompileOnly(module(group = "mcp.mobius.waila", name = "wthit-api", version = "neo-12.4.1"))

                modCompileOnly(module(group = "me.shedaniel", name = "RoughlyEnoughItems-neoforge", version = "16.0.788"))
            }
        }
    }

    mappings {
        official()
        parchment("2024.11.17")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                name.set("Tempad")
                url.set("https://github.com/terrarium-earth/revelationary")

                scm {
                    connection.set("git:https://github.com/terrarium-earth/revelationary.git")
                    developerConnection.set("git:https://github.com/terrarium-earth/revelationary.git")
                    url.set("https://github.com/terrarium-earth/revelationary")
                }

                licenses {
                    license {
                        name.set("lGPL3")
                    }
                }
            }
        }
    }
    repositories {
        maven {
            setUrl("https://maven.resourcefulbees.com/repository/terrarium/")
            credentials {
                username = System.getenv("MAVEN_USER")
                password = System.getenv("MAVEN_PASS")
            }
        }
    }
}
