# Movie App

This repository contains the source code for a movie application that uses the The **Movie Database API**. This app is a fully functional Android app built entirely with Kotlin and Jetpack Compose. It follows Android design and development best practices and is intended to be a useful reference for developers. The application is well-documented and follows best practices like Google's **NowInAndroid** repository for code reusability. It is also user-friendly and easy to navigate.

You can download the APK to install on you device with this link 👇
<a href="https://mega.nz/file/xAhHzRga#3V-JMniLm5gEO7JP5wjEA71VqrpLDlo937rbMus6We0"><img src="https://www.pngall.com/wp-content/uploads/2/Download-Button-PNG-File-Download-Free.png" height="70"></a>

# Features
The application has four main screens:

-   **Home**: This screen lists movies from the following categories: **Now Playing, Popular, Top Rated, and Upcoming**.
-   **Movie Details**: This screen displays detailed information about a movie, such as its **poster, title, genre, release date, vote average, and vote count and etc**.
-   **Search**: This screen allows users to search for movies by title or keyword.
-   **Favorites**: This screen lists all of the movies that the user has favorited.
- ## Screenshots

![Screenshot showing Home, Detail, Search and Favorite screens](docs/images/screenshot.png "Screenshot showing Home, Detail, Search and Favorite screens")


# Architecture
The **Movie** app follows the [official architecture guidance](https://developer.android.com/topic/architecture) and is built on **MVI** architecture. The Model-View-Intent (MVI) architecture is a design pattern used in Android app development. It focuses on unidirectional data flow and separation of concerns to create more predictable and testable code. Here's a brief overview of the MVI architecture in Android:

**Model ,View ,Intent ,ViewModel ,Reducer, Dispatcher**

## The application is built using the following technologies:
-   Kotlin
-   MVI architecture
-   Jetpack Compose
-   Material Design 3
-   Dagger / Hilt
-   The Movie Database API
-   Room database
-   Kotlin Coroutines
-   Kotlin Flow

# UI
The app was designed using  [Material 3 guidelines](https://m3.material.io/). Right now it's only in dark mode but it can be setup as light mode and use dynamic colors (for Android 12+) by just setting the proper colors.

The Screens and UI elements are built entirely using  [Jetpack Compose](https://developer.android.com/jetpack/compose).

# Modularization
The  **Movie**  app has been fully modularized and it contains app, core and features modules.
The app module, contains the logic for the Application and single Activity class for showing the **composable screens** application and app's navigation using **Navigation Compose**.
The core module, contain various modules inside it that providing data to the feature modules. It has:

- **Common**: Includes all the util classes using across the app's modules
- **Data**: Manages data operations, abstracts data sources, and provides a consistent interface for data retrieval and storage.
- **Database**: Handles data persistence and provides methods for storing and retrieving data from local databases. **(Room ORM)**
- **Domain**: Contains the business logic and defines the operations and rules that govern the application's behavior.
- **Model**: represents the data structures and entities used within the application, facilitating data manipulation and communication between different layers.
- **Network**: handles network operations, such as making API requests, handling responses, and managing network connectivity.


# Development Environment
**Move App**  uses the Gradle build system and can be imported directly into Android Studio.

Change the run configuration to  `app`.

# Build
To build and run the MovieApp, follow these steps:
1. Clone the repository.
2. Open the project in Android Studio.
3.  Ensure that your Android SDK is up to  date  with the specified `minSdk` and `targetSdk` versions. 4. Build and run the application on an emulator or physical device.