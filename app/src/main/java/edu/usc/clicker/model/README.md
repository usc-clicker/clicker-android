# model

This directory contains Java classes that represent JSON data models used by our API.

These classes are annotated using Gson for automatic serialization and deserialization and are used to convert API responses from JSON to POJOs (plain old Java objects) and API request bodies from POJOs to JSON.

If you need to make a new class, Gson annotations are quite simple and there are plenty of classes here for reference. For larger JSON models, it might be simpler to use a converter to automatically generate a Java class based on sample JSON. [jsonschema2pojo](http://www.jsonschema2pojo.org) is a handy tool for doing this, just make sure "Source type" is set to JSON and "Annotation style" is set to Gson.

## AnswerResponse

Used as a request body when a user answers a question. Contains a quiz id, question id, user email, lat/long location pair, and the answer itself.

## Auth

Returned to the client app as a response body when logging into the application. Contains user info, very little of which we actually hold onto at this point but much of it could be useful down the road.

## Course

Returned when getting user courses, enrolling, and unenrolling from a class.

## EnrollBody

Used as a request body when enrolling in a class.

## FreeResponseQuestion

Delivered via Parse when a free response question is asked.

## LocationBody

Latitude/longitude pair used in AnswerResponse to provide location when answering a question.

## LoginBody

Used as a request body when logging into Clicker.

## MultipleChoiceQuestion

Delivered via Parse when a multiple choice question is asked.

## NumericResponseQuestion

Delivered via Parse when a numeric response question is asked.

## QuizStatistics

Returned when statistics for a class are requested.

## RegisterBody

Used as a request body when creating a Clicker account.

## Section

Returned when enrolling and unenrolling from a class, and when getting a user's enrolled classes.

## User

Returned when getting a user by id.