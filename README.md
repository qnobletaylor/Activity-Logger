# Activity Logging Application

![AppDemo](https://i.imgur.com/P2G4iZE.gif)

### Overview

This web app allows for a user to add activities to a log which get stored in a memory database.
Meaning that the records stored will be saved only during the session that the web-app runs for.
There is also functionality so that records can be easily deleted or edited by clicking on the corresponding
buttons in their row on the html table.

The purpose of this web-app was to practice using Spring-boot and Thymeleaf primarily with help from Bootstrap for
styling and some functionality. Thus, everything was created using these resources, even if it may have been easier
to use Javascript for certain things (such as the editing modal).

### Instructions

To run the web-app, navigate to the root folder and execute `mvn spring-boot:run` after which you
will be
able to
connect to the homepage which is hosted at [localhost:8080](http://localhost:8080).

### Primary Dependencies

* Spring-boot
* Maven
* Thymeleaf
* Java Persistence API
* H2 Database
* Bootstrap
