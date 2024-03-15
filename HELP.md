## ToDos Web Application
This application will help you manage all your todo items

### Requirements

For building and running the application you need:

- [JDK 21](https://jdk.java.net/21/)
- [Maven 3](https://maven.apache.org)
- [Docker Desktop](https://www.docker.com/products/docker-desktop/) (not a must)
- [Postman](https://www.postman.com/)

### Running application locally using docker-compose
1. Clone the project from gitHub
2. Build project (without tests) using 'mvn install -DskipTest=true'
3. Open terminal from the main project directory (where docker files are located)
4. Run 'docker-compose up'
5. Now you can open Postman and play around with the different API calls:
- [create](http://localhost:8080/todos-api/create)
- [create-list](http://localhost:8080/todos-api/create-list)
- [get-all](http://localhost:8080/todos-api/all)
- [get-by-id](http://localhost:8080/todos-api/id/{id})
- [update-description](http://localhost:8080/todos-api/update/description/{id}/{description})
- [update-statue](http://localhost:8080/todos-api/update/status/{id}/{status})
- [delete](http://localhost:8080/todos-api/delete/{id})

