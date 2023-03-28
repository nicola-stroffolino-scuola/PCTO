# Keycloak for Spring

## Creating the Application

+ Go to https://start.spring.io/

---

+ Create a realm with the preferred name, in our case it will be **EngynyaRealm**.

+ 

---

+ Installing [Dependencies](https://github.com/curityio/spring-boot-oauth-client/blob/master/build.gradle) for Gradle is essential in order to let the Spring Boot application interact with Keycloak.
+ Make sure to change your `distributionUrl` variable to the correct **gradle version** in your `gradle-wrapper.properties` file located in `/gradle/wrapper/`.
  + E.g. I changed it to https\://services.gradle.org/distributions/gradle-8.0.2-bin.zip
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
