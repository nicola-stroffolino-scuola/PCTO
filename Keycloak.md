# Keycloak for Spring

+ Installing [Dependencies](https://github.com/curityio/spring-boot-oauth-client/blob/master/build.gradle) for Gradle is essential in order to let the Spring Boot application interact with Keycloak.
+ Make sure to change your `distributionUrl` variable to the correct **gradle version** in the file located in `/gradle/wrapper/gradle-wrapper.properties`.
  + E.g. I changed it to https\://services.gradle.org/distributions/gradle-8.0.2-bin.zip

---

+ https://gist.github.com/ThomasVitale/5544d276479d3895f4e8632720f5f92b
+ Start keycloack server bin/kc.sh start-dev --http-port=8081
+  
