# Videogame Rental Demo

## Decisions about the Microservice

* I followed a DDD approach, I decided this to have a better code structure and a clear separation for the business logic.
* I Didn't include a full catalog management for the Games, because it would take me some time, but I did added an endpoint to list Games.

## How to run

```bash
mvn clean install
mvn spring-boot:run
```
The service will be receiving requests throw the URI http://localhost:8080

And you can use this [Useful Requests](req.http) to make calls to the endpoint. 

You can use [REST Client](https://marketplace.visualstudio.com/items?itemName=humao.rest-client) on [VSCode](https://code.visualstudio.com/), or [Postman](https://www.postman.com/).