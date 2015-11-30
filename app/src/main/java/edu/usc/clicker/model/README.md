# model

This directory contains Java classes that represent JSON data models used by our API.

These classes are annotated using Gson for automatic serialization and deserialization and are used to convert API responses from JSON to POJOs (plain old Java objects) and API request bodies from POJOs to JSON.

If you need to make a new class, Gson annotations are quite simple and there are plenty of classes here for reference. For larger JSON models, it might be simpler to use a converter to automatically generate a Java class based on sample JSON. [jsonschema2pojo](http://www.jsonschema2pojo.org) is a handy tool for doing this, just make sure "Source type" is set to JSON and "Annotation style" is set to Gson.

## AnswerResponse

Used as a request body when a user answers a question. Contains a quiz id, question id, user email, lat/long location pair, and the answer itself.

## Auth

Returned to the client app as a response body when logging into the application. Contains user info, very little of which we actually hold onto at this point but much of it could be useful down the road.

## Course

Returned