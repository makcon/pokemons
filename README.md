# Pokemons service

## Structure of the service

The service is written in Java 15 and based on spring-boot 2 framework. Maven is using for building the project. The service is following to hexagonal architecture and contains 4 modules:

- `application`: middleman between REST API and domain. Also, it initializes all needed resources and starts the service  
- `domain`: contains all business logic
- `infra`: knows about persistence layer and has connections to database and external pokemon api service
- `dto`: keeps dto objects needed for api (having them separately later we can create a java client for the service)

## How to build and run the service

Note 1: The service uses an embedded database for storing data hence it is not necessary to install something else.
Note 2: During the startup the service will initialize pokemons by requesting external pokemon api. To speedup the process it requests first 100 pokemons. In case if all data needed when simply set the param `pokemons-api.initializer.first-batch-only` to `false`.

### Using an IDE

1. Clone or download the repository
2. Open the project in your favorite IDE (e.q. Intellij Idea)
3. Run spring-boot `Application` class of `application` module
4. The service will be running on the port `8080`

### Using command line

1. Clone or download the repository
2. Go to the root of the project
3. Build the project using `mvn clean package`
4. Start the service: `java -jar application/target/application-1.0-SNAPSHOT.jar`
5. The service will be running on the port `8080`

### Using Docker

1. Clone or download the repository
2. Go to the root of the project
3. Build the project using `mvn clean package`
4. Run the app using the command: `docker-compose up --build` 
5. The service will be running on the port `8080`

## How to use the service

1. Open swagger-api ui: `http://localhost:8080/swagger-ui.html`
2. Here you can see all available endpoints and models and try to execute examples

### TODO list

- Pokemon versions stored in DB as separated string but it's not flexible. For example, it would be tricky to filter if we want to find entities by multiple versions. To fix that create a separate table for versions to normalize the structure
- Handle possible errors, e.q. external api errors, json mapping errors 