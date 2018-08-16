# REST API for Gree Airconditioner

A REST Interface for controlling a Gree Airconditioner. The code is written in Java based on the hard work from the guys working on and contributing to https://github.com/tomikaa87/gree-remote. I take no credit for the research that went into understanding how the interface works. Thanks a lot guys for your research!

## Required

- at least maven 3.5 
- java 8

## Build

```
mvn clean install
```

## Run

```
java -jar target/airconditioner-remote-1.0-SNAPSHOT.jar 
// if it's not working just check the name of the jar in target folder
```

## Where can I see the API?

We have swagger and here it is:
```
http://localhost:8080/swagger-ui.html
```

## Run it on a different port

The app is written using Spring Boot.  
Just search for additional parameters for Spring Boot on google and you'll figure it out :)
