# AppGitHubRepo

The application fetches the name of the repositories from GitHub for the given user.
Application accept http requests to GET http://localhost:8080/repo/{userName} with header "application/json".

The return value is:

- Repository Name
- Owner Login
- For each branch it’s name and last commit sha

The result of the query is saved as a simple object containing the login and name of the repository. The database used is postgresql.
For full operation of the database, a Docker container was used, so before using the application, enable Docker Desktop and initialize the docker-compose.yml file.

You can see the endpoint in Swagger at the link: http://localhost:8080/swagger-ui/index.html#

The application uses SpringBoot, FeignClient, Log4J and Lombook.