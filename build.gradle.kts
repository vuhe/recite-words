buildscript {

    val kotlinVersion: String by extra("1.6.21")
    val springVersion: String by extra("2.7.0")

    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }

    dependencies {
        // kotlin base plugin
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")

        // spring boot plugin
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springVersion")

        // android plugin, bind to gradle version, see
        // https://developer.android.google.cn/studio/releases/gradle-plugin?hl=zh-cn
        classpath ("com.android.tools.build:gradle:7.2.0")
    }
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }
}
