# Clicker-Android

## How to build and run
- Clone this repo
- Open project in Android Studio
- Build and deploy to an Android device or emulator

## Architecture notes
- Clicker for Android exclusively uses Activities and custom views rather than fragments.
- A custom Application is used to handle initialization of global services and resources, such as:
    - Parse (used for receiving push notifications)
    - Retrofit API adapter for answering questions and any other call that hits our API
    - Helpers for location reporting and course registration (more on this later)
    - An ongoing notification to inform the user that the applicaton is in its default "auto-launch" mode
- **Detail on individual components can be seen by browsing to its location in this repo.**

## UI design notes
- Clicker for Android was designed with the Material Design guidelines in mind.
- The [Material Design Guidelines](http://www.google.com/design/spec/material-design/introduction.html) is an excellent and comprehensive document produced by Google that covers every aspect of designing applications for Android.
- It's important that Clicker for Android follows (and continues to follow) this design language and methodology to ensure that it has an interface consistent with other apps familiar to Android users.
    - Clicker should be a pleasure to use and the least difficult part of answering questions in class.

## Suggestions for future developers
- Questions to ask when implementing a feature:
    - Is this the simplest way to convey this information or perform this action?
    - Are we doing it in a way that's consistent with accepted Android UI metaphors?
        - i.e. don't use iOS-style bottom tabs instead of the top tabs common in Android apps
    - Is it functional, visually clear, and aesthetically pleasing?
- Suggested features and fixes: