| Dependencies UpToDate | License |
|:---------------------:|:-------:|
| [![Dependencies UpToDate](https://ci.reinvent-software.de/buildStatus/icon?job=play-hibernate-template-DependencyCheck)](https://ci.reinvent-software.de/job/play-hibernate-template-DependencyCheck) | [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) |

Play Hibernate Template
=======================

# Abstract
A java [Play](https://www.playframework.com/) 2.4.3 [Giter8](http://www.foundweekends.org/giter8/) template with Hibernate.

## Usage
`sbt new ldaume/play-hibernate.g8`

## Introduction
A starting template if you want to develop a micro service with hibernate.

Then, we have the following objectives:

  * Development should be simple. `activator run` should be enough to run all services at the same time.
  * Common code, dependencies and modules should be easily shared.
  * We should be able to compile, test and run each service separately in development and production.
  * We should distribute each service separately.
  * It should be a template ready to use with the following features:
    * Dependency Injection with Guice
    * Testable JPA
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
