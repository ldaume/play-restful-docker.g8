# Abstract
A RESTful java [Play](https://www.playframework.com/) 2.4.3 [template](https://www.typesafe.com/activator/template/play-hibernate) with Docker.

## Usage
`activator new PROJECTNAME play-restful-docker`

## Introduction
A starting template if you want to develop a RESTful micro service with docker.

Then, we have the following objectives:

  * Development should be simple. `activator run` should be enough to run all services at the same time.
  * Common code, dependencies and modules should be easily shared.
  * We should be able to compile, test and run each service separately in development and production.
  * We should distribute each service separately.
  * It should be a template ready to use with the following features:
    * Dependency Injection with Guice
    * Dockerizable
  * It shoud explain:
    * How to share every common code to avoid duplications (models, controllers, ...).
    * How to use it for development, test and production.

## Binding
This template uses the dependency injected router [Dependency Injection](https://www.playframework.com/documentation/2.4.x/JavaRouting)
## Dependency-Injection
So, every route starts with an @ like `@controllers.Application.index()` which leads to non-static actions.

## Logging

The logger is configured for a better output and contains a rolling file
appender. The logs are look like
`2015-08-17 10:21:17,914 INFO [ForkJoinPool-1-worker-3] application: Class::Method:#Line - Message`.
