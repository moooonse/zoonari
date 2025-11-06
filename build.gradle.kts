// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false

    // --- LÍNEA CORREGIDA para que coincida con el TOML ---
    alias(libs.plugins.googleGmsGoogleServices) apply false
}

