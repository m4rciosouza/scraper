[![Build Status](https://travis-ci.org/m4rciosouza/scraper.svg?branch=master)](https://travis-ci.org/m4rciosouza/scraper)
# Web Scraper
Web scraper that generates a JSON from a website, collecting product's details.


## Stack
* Java 8+
* Spring Boot 2.x
* Jsoup
* Gradle
* Travis CI

## Requirements
* Java 8+
* Internet connection

## Running the tests
The easiest way to run the tests is using its embedded Gradle wrapper, running the following command in the terminal: 

`./gradlew test`

## Running the application
The easiest way to run the application is using its embedded Gradle wrapper, running the following command in the terminal: 

`./gradlew bootRun`

## Output
The application after started will start the scrape process, outputing the JSON file in the stdout, and terminate with exit code 0.

If something goes wrong during the process, such as Internet connection issues, the application will display an error message and terminate with error code 1.

#### Note for Windows users
If you are going to run the tests or the application on Windows, use the above commands, replacing the daemon 
`./gradlew` to `gradlew.bat`, followed by `test` or `bootRun` respectively to test and run the application.
 
