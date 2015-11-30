# activity

This directory contains all Activities used in Clicker for Android.

## CreateAccountActivity
Handles account creation.

Launched by WelcomeActivity.

## FOSSActivity
A simple Activity containing a scrolling TextView with open source licenses for the libraries used in this application.

Launched by MainActivity.

## FreeResponseActivity
An Activity for answering free response questions.

Launched by ClickerPushBroadcastReceiver (either automatically or when a user opens a notification from Clicker).

## LoginActivity
Handles logging into Clicker.

Launched by WelcomeActivity.

## MainActivity
The default Activity launched when a user opens Clicker.

If the user is not logged in, MainActivity starts WelcomeActivity. If the user is logged in, MainActivity gets Clicker ready to receive questions by initializing Parse, LocationHelper, and SectionHelper.

## MultipleChoiceActivity
An Activity for answering multiple choice questions.

Launched by ClickerPushBroadcastReceiver (either automatically or when a user opens a notification from Clicker).

## MyClassesActivity
An Activity that displays a list of the user's registered classes. The user can register for a class by tapping the add button, unregister for a class by long pressing on a class, and view quiz statistics for a class by pressing on a class.

Launched by MainActivity.

## NumericResponseActivity
An Activity for answering numeric response questions.

Launched by ClickerPushBroadcastReceiver (either automatically or when a user opens a notification from Clicker).

## ResponseActivity
An Activity superclass extended by FreeResponseActivity, MultipleChoiceActivity, NumericResponseActivity. Could be used to implement common functionality between the three different response Activities (like the response timer and sending question responses).

## StatisticsActivity
An Activity that displays a list of quiz scores for a certain class.

Launched by MyClassesActivity.

## WelcomeActivity
An Activity that welcomes users to the app and gives them the option of signing in or creating an account.

Launched by MainActivity.