## ToDos Web Application
This application will help you manage all your todo items.

### Requirements

For building and running the application you need:

- [JDK 21](https://jdk.java.net/21/)
- [Maven 3](https://maven.apache.org)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (not a must)
- [Postman](https://www.postman.com/) (not a must)

### Running application locally using docker-compose
1. Clone project from gitHub
2. Build project using 'mvn install'
3. Open terminal from the main project directory (where docker files are located)
4. Run 'docker-compose up'
5. Run supported api calls:
### Create
    http://localhost:8080/todos/create
##### body
    {"title":"workout","description":"go to pilates"}
### Create list
    http://localhost:8080/todos/create-list
##### body
    [{"title":"workout","description":"go to pilates"},{"title":"laundry","description":"wash the kids pile"},{"title":"relax","description":"go to pilates"}]
### Get all
    http://localhost:8080/todos/
### Get by id
    http://localhost:8080/todos/{id}
### Update-status
    http://localhost:8080/todos/update/status/{id}/{status}
### Delete
    http://localhost:8080/todos/delete/{id}

### Clarifications
1. The status is set to TODO for each newly created item
2. Time and date are set at creation
3. Title and description of todo item cannot be null
4. Todo status can be TODO, IN_PROCESS or DONE
5. The todo items are cached when retrieved, additional properties can be added to define caching size, cleanup and expiration.
