# Spring Boot tutorial

## Introduction

This is the Spring Data JPA project ,which is basically for performing the CRUD operations
on database by using Spring Data JPA Concepts.Alongwith CRUD operations, we will learn how
to perform Unit and Integration test.

## Running the application

The main file of this application is TutorialApplication.To run the file,right click on it
and run

### Running Checkstyle & Build

mvn clean checkstyle:check verify

### Running Sonar locally

```shell
mvn -B verify org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=jyotikakrani25_SpringBoot_Tutorial -Dsonar.login=<Token_For_Your_User_Id>
```

view results in https://sonarcloud.io/dashboard?id=jyotikakrani25_SpringBoot_Tutorial

## References

Attached references link with each feature section,which you can find under Issues.