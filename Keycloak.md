# Keycloak for Spring

## Creating the Application

+ Go to https://start.spring.io/

## Setting up Keycloak

+ Create a Realm with a preferred name, in our case it will be ***EngynyaRealm***.

+ Create a Client with a preferred name (**Client ID**), in our case it will be ***engynya-login***.
  - Change the **Valid Redirect URIs** field in Access Settings to `http://localhost:8081/*`, the port that we'll be runnin our application at.

+ Create a Role by navigating to the *Realm Roles* page. Keycloak uses **Role-Based Access**, therefore each user must have a role.
  - We'll create a basic ***user*** role.

+ Create a User by navigating to the *Users* page we'll create a first user named ***user1***.
  - Once created we'll be directed to the user *Details* page and from there we'll switch to the *Credentials* tab.
  - There we can setup an initial password for the user for him to be able to login.
  - Finally we'll navigate to the *Role Mappings* tab and there we'll be assigning the ***user*** role to our ***user1***.

## Generating Access Tokens

Keycloak provides a REST API for generating and refreshing access tokens. We can easily use this API to create our own login page.

+ First things first we need to acquire an access token by sendin a POST request to http://localhost:8081/realms/EngynyaRealm/protocol/openid-connect/token. The request should have this body in a **x-www-form-urlencoded** format :
  ```
  client_id:<your_client_id>
  username:<your_username>
  password:<your_password>
  grant_type:password
  ```
  Meaning that that an ideal curl POST request would look like :
  ```curl
  curl -L -X POST 'http://localhost:8081/realms/EngynyaRealm/protocol/openid-connect/token' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  --data-urlencode 'client_id=engynya-login' \
  --data-urlencode 'grant_type=password' \
  --data-urlencode 'username=user1' \
  --data-urlencode 'password=12345'
  ```
  Where :
  - `client_id` is the one with set up in the *Clients* page, in our case ***engynya-login***
  - `username` is self-explanatory, in our case it's ***user1***
  - `password` is the user password, in our case it's ***12345***



---

+ Installing [Dependencies](https://github.com/curityio/spring-boot-oauth-client/blob/master/build.gradle) for Gradle is essential in order to let the Spring Boot application interact with Keycloak.
+ Make sure to change your `distributionUrl` variable to the correct **gradle version** in your `gradle-wrapper.properties` file located in `/gradle/wrapper/`.
  - E.g. I changed it to https\://services.gradle.org/distributions/gradle-8.0.2-bin.zip
+ Add these variables to your `application.properties` file located in `/src/main/resources/`:
  ```properties
  # Client Registration Configuration
  spring.security.oauth2.client.registration.keycloak.client-id=engynya-login
  spring.security.oauth2.client.registration.keycloak.authorization-grant-type=authorization_code
  spring.security.oauth2.client.registration.keycloak.scope=openid

  # OIDC Provider Configuration
  spring.security.oauth2.client.provider.keycloak.issuer-uri=http://localhost:8081/realms/EngynyaRealm
  spring.security.oauth2.client.provider.keycloak.user-name-attribute=preferred_username
  
  # Validating JWT Token
  spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8081/realms/EngynyaRealm
  ```
  Where :
  - The value we specify in `client-id` matches the client we named in the admin console.
  - The `keycloak.issuer-uri` and `jwt.issuer-uri` are the URIs or the authorization server.



---

+ https://gist.github.com/ThomasVitale/5544d276479d3895f4e8632720f5f92b
+ Start keycloack server bin/kc.sh start-dev --http-port=8081
+ Keycloak Endpoints: https://github.com/keycloak/keycloak/tree/9cbc335b68718443704854b1e758f8335b06c242/services/src/main/java/org/keycloak/protocol/oidc/endpoints
+ https://stackoverflow.com/questions/75384815/spring-boot-application-always-redirect-to-login-despite-request-having-valid-ac
+ https://github.com/ch4mpy/spring-addons/tree/master/samples/tutorials/reactive-client
+ Essential : https://medium.com/geekculture/using-keycloak-with-spring-boot-3-0-376fa9f60e0b
