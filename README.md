# Reading Is Good

ReadingIsGood is an online books retail firm which operates only on the Internet.
Main target of ReadingIsGood is to deliver books from its one centralized
warehouse to their customers within the same day.

## Table Of Contents

1. [Service Documentation](#service-documentation)
2. [Application Components](#application-components)
3. [Technology Stack](#technology-stack)
4. [Project Setup](#project-setup)

## Service Documentation

- https://app.swaggerhub.com/apis/umuterd/ReadingIsGood/0.1

## Application Components

| Package    |                  Description                 |
|------------|----------------------------------------------|
| Controller |  The Rest Interface and Implementation.      |
| Service    |  It contains the business logic              |
| Repository |  It contains all the data repositories       |

## Technology Stack

- Spring Framework 2.6.2
- Cucumber 7.3.3
- MongoDb 2.6.2
- Java 17
- Maven
- Rest

## Project Setup

- git clone https://github.com/umuterdolu/GetirCaseStudy.git
- mvn clean install
- docker-compose up -d --build