plugins {
    id("org.jetbrains.intellij") version "0.7.2"
    java
    kotlin("jvm") version "1.4.21"
    id("io.franzbecker.gradle-lombok") version "2.1"
    id("net.saliman.properties") version "1.5.1"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

group = "dev.starship"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))


    implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation("com.auth0:java-jwt:3.12.0")
    implementation("com.google.code.gson:gson:2.8.6")
    implementation("com.google.guava:guava:27.1-jre")
    implementation("org.yaml:snakeyaml:1.27")

    implementation("com.github.zafarkhaja:java-semver:0.9.0")
    implementation("io.sentry:sentry:1.7.23") {
        exclude("org.slf4j")
    }

    implementation("com.github.briandilley.jsonrpc4j:jsonrpc4j:1.6") {
        exclude("org.slf4j")
    }

    annotationProcessor("org.projectlombok:lombok:1.18.16")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.16")

    testImplementation("junit", "junit", "4.12")
}

var baseIDE = "IC"
if (project.hasProperty("baseIDE")) {
    baseIDE = project.property("baseIDE") as String
}
val platformVersion = prop("platformVersion").toInt()
val ideaVersion = prop("ideaVersion")
val starshipVersion = prop("version")

val terminalPlugin = "terminal"
val javaPlugin = "com.intellij.java"
val phpPlugin = "com.jetbrains.php:" + prop("phpPluginVersion")
val goPlugin = "org.jetbrains.plugins.go:" + prop("goPluginVersion")

version = "$starshipVersion-$platformVersion"

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
    version = ideaVersion
    val plugins = mutableListOf(
        terminalPlugin,
        javaPlugin,
        phpPlugin,
        goPlugin
    )
    setPlugins(*plugins.toTypedArray())
    pluginName = "starship-intellij-plugin"
    updateSinceUntilBuild = true
}

sourceSets {
    main {
        java.srcDirs("src/$platformVersion/main/java")
    }
}

tasks.runIde {
    if (baseIDE == "GO") {
        ideDirectory("/Applications/GoLand.app/Contents")
    }
    if (baseIDE == "PHP") {
        ideDirectory("/Applications/PhpStorm.app/Contents")
    }
}

tasks {
    patchPluginXml {
        pluginId("dev.starship.starship-intellij-plugin")
        pluginDescription(
            """
            <html>
                <p>
                    starship for JetBrains brings the power and convenience of IDEs to cloud-native Kubernetes application development. It helps you to increase agility and speed to develop cloud-native applications on Kubernetes.
                </p>

                <h3>
                    Key Features
                </h3>

                <p>
                    <ul>
                        <li>
                            <b>Start cloud-native application development in one click</b> - starship helps you spend less time on environment configuration, you can easily connect to any Kubernetes environment in one click, and focus on developing your app. 
                        </li>
                        <li>
                            <b>Fast deployment</b> - You can deploy any Manifest Yaml, Helm and Kustomize applications by just few clicks.
                        </li>
                        <li>
                            <b>See code change under a second</b> - Automatically synchronize the code to container every time you make a change. starship eliminate the submit, building and pushing cycles,  significantly speed up the feedback loop of development, so you see change in under a second.
                        </li>
                        <li>
                            <b>Easy debugging in remote Kubernetes</b> - starship provides the same debugging experience you've used in the IDE even when debugging in remote Kubernetes cluster.
                        </li>
                    </ul>
                </p>

                <h3>
                    Resources
                </h3>
                <p>
                    <ul>
                        <li>
                            <a href="https://starship.dev/eng/getting-started/"><b>Quick start</b></a> - Follow our quick start to enjoy the faster and easier cloud-native application.
                        </li>
                        <li>
                            <a href="https://starship.dev/"><b>Documentation</b></a> - We have a lot of features to explore. Head over our documentation to discover more.
                        </li>
                        <li>
                            <a href="https://starship.slack.com/"><b>Talk to us</b></a> - Connect to the starship development team by joining our Slack channel. 
                        </li>
                        <li>
                            <a href="https://github.com/starship/starship/issues"><b>File a issue</b></a> - If you discover any issue, file a bug and will fix it as soon as possible.
                        </li>
                    </ul>
                </p>
            </html>
            """.trimIndent()
        )
        changeNotes(
            """
            <h2>Version 0.4.19</h2>
            
            <h3>New Features</h3>

            <ul>
                <li>
                    Supports JetBrains IDEs 2021.2
                </li>
                <li>
                    Supports namespace PVC clear
                </li>
            </ul>

            <h3>Refactor & Improvement</h3>

            <ul>
                <li>
                    Host key checking skip for cloning git repository over SSH
                </li>
                <li>
                    A more friendly notification for nhctl command messages
                </li>
                <li>
                    PHP remote debugging experience
                </li>
                <li>
                    Add cluster status display
                </li>
                <li>
                    Add guide message
                </li>
            </ul>

            <h3>Bug Fixes</h3>

            <ul>
                <li>
                    NPEs
                </li>
            </ul>

            <h2>Version 0.4.15</h2>

            <h3>New Features</h3>

            <ul>
                <li>
                    Supports PHP remote debugging
                </li>
                <li>
                    Supports application upgrade
                </li>
                <li>
                    Skip SSH host key checking on git clone
                </li>
            </ul>

            <h3>Refactor & Improvement</h3>

            <ul>
                <li>
                    Move edit manifest action to context menu
                </li>
                <li>
                    A more friendly notification for "Server version outdate"
                </li>
            </ul>

            <h3>Bug Fixes</h3>

            <ul>
                <li>
                    NPEs
                </li>
                <li>
                    Fixed "get config by nhctl" issue in run and debug mode
                </li>
            </ul>


            <h2>Version 0.4.14</h2>
            
            <h3>New Features</h3>
            
            <ul>
                <li>
                    Supports automatically refresh starship Server side token
                </li>
            </ul>

            <h3>Bug Fixes</h3>

            <ul>
                <li>
                    Fixed the "unable to save configuration" issue
                </li>
            </ul>


            <h2>Previous Changelogs</h2>
            
            <p>
                <a href="https://github.com/starship/starship/tree/main/CHANGELOG">https://github.com/starship/starship/tree/main/CHANGELOG</a>
            </p>
          """
        )
    }

    publishPlugin {
        token(System.getenv("JETBRAINS_TOKEN"))
    }

    buildSearchableOptions {
        enabled = false
    }
}

tasks.withType(JavaCompile::class) {
    options.compilerArgs.add("-Xlint:unchecked")
    options.compilerArgs.add("-Xlint:deprecation")
}

fun prop(name: String): String =
    extra.properties[name] as? String
        ?: error("Property `$name` is not defined in gradle.properties")
