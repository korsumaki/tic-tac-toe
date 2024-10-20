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

## Tests

    ./gradlew desktopTest
    ./gradlew testDebugUnitTest
    ./gradlew testReleaseUnitTest

**NOTE** Stop gradlew task with CTRL-C

## TODO Stuff
  - ViewModel
    + callback func to update tapped info to viewmodel
    + fill marks from viewmodel
  + Algorithm to work behind viewmodel
    + Check winner
    + Calculate next move
  - Platforms
    - wear os
  - Fine tuning
    - Enable tapping to whole box (web, desktop?)
    - scale UI properly (dynamically) in all platforms (android, web, desktop)
    - responsive screen size
  - UI development
    + UI scrolling to allow larger play field
  - Own development workflow
    + use compose preview
    + test with device or desktop
      **- Ctrl-Shift-R in testcase or test class
      - Ctrl-R to rerun tests**
    - fast way to build from IDE?
  - Testing
    - Compose testing
    - commands to execute tests?
    - platform specific tests (if needed)
    - LeakCanary
    - Crashlytics
  - Distribution
    - release versions: android apk signing
    - desktop distribution? What is needed?
    - icon
    - Play Store stuff
    - Version string
  - CI build
    - store lint file
    - run tests
  - dependabot
  - study version dependencies
    - Kotlin vs Kotlin multiplatform vs Compose multiplatform
  - unit tests (kotlin tests)
    - https://kotlinlang.org/docs/multiplatform-add-dependencies.html#test-libraries
