## ToDos Web Application
This application will help you manage all your todo items.
The application uses caching for improved preformance.

### Requirements

For building and running the application you need:

- [JDK 21](https://jdk.java.net/21/)
- [Maven 3](https://maven.apache.org)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (not a must)
- [Postman](https://www.postman.com/) (not a must)

### Running application locally using docker-compose
1. Clone the project from gitHub
2. Build project using 'mvn install'
3. Open terminal from the main project directory (where docker files are located)
4. Run 'docker-compose up'
5. Run supported api calls:
### create
    http://localhost:8080/todos-api/create
#### body example
    {"title":"workout","description":"go to pilates"}
### create-list
    http://localhost:8080/todos-api/create-list
#### body example
   [
    {
    "title":"workout",
    "description":"go to pilates"
    },
    {
    "title":"laundry",
    "description":"wash the kids pile"
    },
    {
    "title":"relax",
    "description":"go to pilates"
    }
]
### get-all
    http://localhost:8080/todos-api/all
### get-by-id
    http://localhost:8080/todos-api/id/{id}
### update-status
    http://localhost:8080/todos-api/update/status/{id}/{status}
### update-entity (overrides the entity with same id)
    http://localhost:8080/todos-api/update
#### body example
    {"id":1,"title":"workout","description":"go to pilates","createdTime":"2024-03-16T06:06:26.525725","status":"DONE"}
### delete
    http://localhost:8080/todos-api/delete/{id}
