# Keycloak for Spring

## Creating the Application

+ Go to https://start.spring.io/

---

+ Create a Realm with a preferred name, in our case it will be ***EngynyaRealm***.

+ Create a Client with a preferred name (**Client ID**), in our case it will be ***engynya-login***.
  - Change the **Valid Redirect URIs** field in Access Settings to `http://localhost:8081/*`, the port that we'll be runnin our application at.

+ Create a Role by navigating to the *Realm Roles* page. Keycloak uses **Role-Based Access**, therefore each user must have a role.
  - We'll create a basic ***user*** role.

+ Create a User by navigating to the *Users* page we'll create a first user named ***user1***.
  - Once created we'll be directed to the user *Details* page and from there we'll switch to the *Credentials* tab.
  - There we can setup an initial password for the user for him to be able to login.
  - Finally we'll navigate to the *Role Mappings* tab and there we'll be assigning the ***user*** role to our ***user1***.

---

+ Installing [Dependencies](https://github.com/curityio/spring-boot-oauth-client/blob/master/build.gradle) for Gradle is essential in order to let the Spring Boot application interact with Keycloak.
+ Make sure to change your `distributionUrl` variable to the correct **gradle version** in your `gradle-wrapper.properties` file located in `/gradle/wrapper/`.
  - E.g. I changed it to https\://services.gradle.org/distributions/gradle-8.0.2-bin.zip
+ Add these variables to your `application.properties` file located in `/src/main/resources/`:
  ```properties
  spring.security.oauth2.client.registration.keycloak.client-id=login-app # 1
  spring.security.oauth2.client.registration.keycloak.authorization-grant-type=authorization_code # 2
  spring.security.oauth2.client.registration.keycloak.scope=openid # 3
  ```
  1. The value we specify in `client-id` matches the client we named in the admin console.
  2. 
---

+ https://gist.github.com/ThomasVitale/5544d276479d3895f4e8632720f5f92b
+ Start keycloack server bin/kc.sh start-dev --http-port=8081
+  
