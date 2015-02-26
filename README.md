#IT-HUSET Spring Boot 101
Welcome to IT-HUSET (http://www.it-huset.se) Spring Boot lab session 1!

This lab session will teach you to set up an end-to-end Spring Boot application that expose a PostgresQL database with a REST API.

The lab contains three parts, and different Git branches build upon the work in a previous part, so if you are stuck, you can always checkout the
next branch. And look at the solution.

The parts are:

##Part 1, Basic code structure and dependencies.
Git branch **master**, git clone ...`

In this part of the lab we create the basic Spring Boot code structure. We also make sure we can package and run our app.

##Part 2, Adding web and REST capabilities with a starter POM.
Git branch **part2**, `git checkout -b origin/part2`

In this part of the lab we add the required dependencies to build a REST API. We also experiment with the externalized configuration.

##Part 3, Connecting to a database and reading data.
Git branch **part3** `git checkout -b origin/part1`

In the last part of the lab, we add a unit test to our app, we also add required dependencies for Spring Data JPA.
Finally we should be able to read data from a PostgresQL database and expose it with our previously build REST API.

##Solution
Git branch **solution** `git checkout -b origin/solution`

To view the solution, checkout this branch. No assignments here!

#Part 1 - Lab assignment
Welcome to the master branch! Your assignment is, if you choose to accept it, to output "Hello IT-HUSET" on the command line.
You have a skeleton ready which does not do much at the moment besides that a parent POM and a Spring Boot Maven plugin has been added.

1. Look at the effective POM. By defining a parent POM, Spring Boot adds several properties important properties.
For instance, `java.version` which defaults to 1.6, but we have overridden to be 1.8.
The added Spring Boot plugin let us package the application as a standalone executable JAR (or war).
1. We now need the base Spring Boot dependency. Add:
```
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter</artifactId>
</dependency>
```
1. From the previous step you can see tat several dependencies was pulled in. The versions are defined in the parent POM.
To update a Spring Boot application, you only need to update the version inthe `<parent></parent>` element.
1. Now time for the application entrypoint. Open `Application.java`and add three annotations to it:
```
@Configuration
@ComponentScan
@EnableAutoConfiguration
```
1. Time to bootstrap the application in the main-method by using the `SpringApplication.run(x,y)` method.
1. Either just to a System.out to output the required salution. For something more fancy, define a Spring bean with the message.
Remember, that your Application.class now is also a Spring bean (it is annotated with `@Configuration`)!
1. On the command line, execute `mvn package`.
1. Run the executable JAR-file

If you see your message, you are finished! Now go to part 2 of this lab by checking out the **part2**-branch.

#Part 2 - Lab assignment
Available in the next Git branch.

#Part 3 - Lab assignment
Available in the next Git branch.