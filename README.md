<h1 align="center">Sahabat Bencana</h1>

<p align="center">  
🗡️ Sahabat Bencana is project from Generasi Gigih 3.0 with demonstrates modern Android development with Coroutines, Flow, Koin, and MVVM architecture.
</p>
<br />

## APK Release
[Google Drive](https://drive.google.com/drive/u/0/folders/1nIOYB5xTQcMnAa9j2oLS820lMFOoddpZ)
<br />


## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [DataBinding](https://developer.android.com/topic/libraries/data-binding) - Binds data directly into XML layouts
  - [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Jetpack DataStore is a data storage solution that allows you to store key-value pairs or typed objects with protocol buffers.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [GSON](https://github.com/google/gson) - A modern JSON library for Kotlin and Java.
- [Timber](https://github.com/JakeWharton/timber) - A simple logging library for android.
- [GSON Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/gson) - A Converter which uses Moshi for serialization to and from JSON.
- [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling
- [Koin](https://github.com/InsertKoinIO/koin) - Koin is a pragmatic lightweight dependency injection framework for Kotlin developers
- [Google Maps](https://developers.google.com/maps/documentation/android-sdk/overview?hl=id) - Implementation Maps of Google
- [Lottie](https://lottiefiles.com/) - Animation loading with Lottie


# Package Structure
    
    com.miftahulhudaf.sahabatbencana    # Root Package
    .
    ├── data                # For data handling.
    |   ├── api             # API Services & Retrofit
    |   ├── di              # Dependency Injection API
    │   ├── repository      # Single source of data.
    │   ├── response        # Model data response API
    │   ├── model           # Model data classes
    │   └── utils           # Helper for date, localdata, etc
    |
    ├── di                  # Dependency Injection
    ├── notifications       # Handle notification work manager
    ├── ui                  # UI/View layer
    |   ├── base            # viewmodelfactory
    │   ├── main            # Activity & Fragments
    │   └── setting         # App Preferences
    └── utils               # Utility Classes / Kotlin extensions


## Architecture
This app uses [***MVVM (Model View View-Model)***](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.

![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)
  
