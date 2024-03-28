# Tic-Tac-Toe

This is a Kotlin Multiplatform project targeting Android, Web and Desktop.
Main purposes of this project is to study and learn Kotlin Multiplatform and Compose Multiplatform.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

## Android

    ./gradlew assembleDebug

## Desktop

    ./gradlew packageUberJarForCurrentOS
    java -jar composeApp/build/compose/jars/com.korsumaki.tictactoe-macos-x64-1.0.0.jar

## Web

    ./gradlew wasmJsBrowserDistribution
    ./gradlew wasmJsBrowserRun

**NOTE** Stop gradlew task with CTRL-C

## TODO Stuff
  + OK: test compilations: +android, +web, +desktop
  + code to github
    + create repo
    + push
  - find own workflow for developing
    - use compose preview
    - test with device or desktop
    - fast way to build from IDE?
  - Some basic UI grid
  - react to tapping
  - Kotlin unit tests
  - compose testing
  - Android Studio
    + debug
    - Preview for UI
      - https://developer.android.com/jetpack/compose/tooling/previews
      - @PreviewScreenSizes, @PreviewFontScales, @PreviewLightDark, and @PreviewDynamicColors
    - command line
      - build Android apk
        - debug
        - release
      - build desktop
        + ./gradlew packageUberJarForCurrentOS
          - composeApp/build/compose/jars/com.korsumaki.tictactoe-macos-x64-1.0.0.jar
      - build web
        + ./gradlew wasmJsBrowserRun
        + ./gradlew :composeApp:wasmJsBrowserDevelopmentRun
        - /wasmJsBrowserDistribution
        - use CTRL-C to stop gradlew after using
    - CI build
      - store lint file
    - dependabot
    - unit tests (kotlin tests)
      - https://kotlinlang.org/docs/multiplatform-add-dependencies.html#test-libraries
