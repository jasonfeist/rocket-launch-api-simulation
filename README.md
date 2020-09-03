
# Rocket Launches API

### Introduction
Spring Boot based REST API for delivering historical data about rocket launches.

### Software Needed
There are two ways to build and run the server, natively or in a container.  For the container version, only Docker is needed.  For the native version, Java 11+ and Gradle 6.3+.

### Instructions - Native Version
To build the code:

`
./gradlew build`

To start the server:

`
java -jar build/libs/app.jar`

### Instructions - Docker Version
To build the image (from the root of the repo):

`
docker build .`

To start the server:

`
docker run -p 8080:8080 <id of the image>`

### Database Settings
The database connection settings are stored in:
src/main/resources/application.properties

### Health
The is a health check endpoint, to test it:

`
curl http://localhost:8080/actuator/health`

### Endpoints

All endpoints use HTTP GET.

* /rocket-launches/average-cost
* /rocket-launches/percent-by-status?status=anyStatus
* /rocket-launches/top-month
* /rocket-launches/count-by-location
* /rocket-launches/count-by-country

All endpoints have optional startDate, endDate, and rocketCompany parameters with which to filter the data.  The date parameters must be specified in yyyy-MM-dd format, and the rocketCompany parameter is case insensitive.  It can be tested via curl.

`
curl 'http://localhost:8080/rocket-launches/count-by-country?rocketCompany=Northrop&startDate=1980-01-01'`

### HTTP Status Codes
All endpoints will return the following status codes:
* 200 = Success
* 400 = If the date was not in yyyy-MM-dd format, or the status is not valid.
* 500 = I hope no one ever sees this