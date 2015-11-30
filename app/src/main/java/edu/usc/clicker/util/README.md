# util

This directory contains various utility classes used by Clicker for Android.

## ClickerLog

A static class that mirrors the functionality in Android's Log class in debug builds. However, on release builds, it makes no calls to prevent unnecessary logging.

## LocationHelper

A class that handles location listening and provides a simplified listener interface.

## LoginHelper

A class that handles keeping track of login credentials using SharedPreferences.

## SectionHelper

A class that keeps track of a user's registered classes. Provides a listener interface that we use to automatically subscribe to and unsubscribe from Parse channels when a user registers and unregisters from a class.

## Timer

A class that fires a callback on a listener every second until time expires. Provides a listener interface for this purpose.