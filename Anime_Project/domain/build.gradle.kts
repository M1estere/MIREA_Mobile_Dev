plugins {
    id("java-library")
    id("com.google.gms.google-services")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}
dependencies {
    implementation("com.google.android.gms:play-services-tasks:18.2.0")
}
