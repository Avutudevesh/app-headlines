# News Headlines App
An Android App which fetches currently trending headlines from https://newsapi.org api and shows the details.

# Features!
    - Show top headlines in a list
    - Shows additional details when clicked on an article
    - Caching of data using room
    - Swipe to refresh

### Tech
This app is built using kotlin programming language and is built using MVVM architecture.
Uses below libraries to perform certain tasks.
    * Dagger2 - For dependency injection
    * Retrofit - For Networking
    * Junit and Roboelectric - For Unit Tests
    * Glide - To fetch images from network
    * Coroutines - To perform async tasks
    * Room - to cache user data

### Installation
* Building the app
./gradlew clean assembleDebug
* Installing the app on emulator or device
./gradlew clean installDebug
* Running unit tests
./gradlew clean test