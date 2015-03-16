#IT-HUSET Spring Boot 101
Welcome to IT-HUSET (http://www.it-huset.se) Spring Boot lab session 1!

This lab session will teach you to set up an end-to-end Spring Boot application that expose a PostgreSQL database with a REST API.

The lab contains three parts, and different Git branches build upon the work in a previous part, so if you are stuck, you can always checkout the
next branch. And look at the solution.

The parts are:

##Part 1, Basic code structure and dependencies.
Git branch **master**. This is the starting point of the lab. By cloning this repository from Github you will checkout the master branch by default.

In this part of the lab we create the basic Spring Boot code structure. We also make sure we can package and run our app.

##Part 2, Adding web and REST capabilities with a starter POM.
Git branch **part2**, `git checkout -b origin/part2`

In this part of the lab we add the required dependencies to build a REST API. We also experiment with the externalized configuration.

##Part 3, Connecting to a database and reading data.
Git branch **part3** `git checkout -b origin/part1`

In the last part of the lab, we add a unit test to our app, we also add required dependencies for Spring Data JPA.
Finally we should be able to read data from a PostgreSQL database and expose it with our previously build REST API.

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
1. Either just add a System.out.println to output the required salution. For something more fancy, define a Spring bean with the message.
Remember, that your Application.class now is also a Spring bean (it is annotated with `@Configuration`)!
1. On the command line, execute `mvn package`.
1. Run the executable JAR-file.
1. Notice the size of the JAR-file. You can also unpack it and study its contents. What do you think?

If you see your message, you are finished! Now go to part 2 of this lab by checking out the **part2**-branch.

#Part 2 - Lab assignment
Nice work on the previous assignment operative.

1. We need a starter POM for our REST API. In Spring Framework this is handled by Web MVC.
Take a look here: http://docs.spring.io/spring-boot/docs/1.2.1.RELEASE/reference/htmlsingle/#using-boot-starter-poms
See anything we might use as a starter POM for web and REST? Add it to `pom.xml`! And remember, do not add a version to the
dependency, let Spring Boot handle that.
1. Take a moment to study and think about all the new (managed) dependencies Spring Boot pulled in.
1. We need a simple POJO which will be serialized to JSON. Create a class, in any package under Application.class.
It should have two fields, `id` and `data`.
1. We also need to create a controller class to serve the JSON data. Create a new class, in a package under Application.class.
Use the following annotations:
```
@RestController
public class XXX {
```
```
@RequestMapping("/myrest")
public MyPojo getPojoStuff() {
```
1. Return your POJO in the method annotated with `@RequestMapping`.
1. Use Maven to package and run the JAR-file like you did in the first part.
1. Wow, a server is started! Spot in the log output which port it is bound to.
1. Fire up your browser and navigate to `http:/localhost:<port>/myrest`.
If you see JSON data in the browser in the format of the POJO you created, then Great Success!
1. Spring Boot handles properties in a smart and easy way. See for instance:
  * http://docs.spring.io/spring-boot/docs/1.2.1.RELEASE/reference/htmlsingle/#boot-features-external-config
  * http://docs.spring.io/spring-boot/docs/1.2.1.RELEASE/reference/htmlsingle/#howto-properties-and-configuration
1. Without changing any code or re-package, lets start the application on another port.
1. Shutdown the currently running app, and start it with `java -jar xxx.jar --server.port=<some port>`

We have barely scratched the surface on how Spring Boot simplifies handling properties and configuration. It is easy to define default config
in property files and then override them when needed without any recompilation.
The reference document nicely describe how this work so be sure to read it.

On to the last part of this lab!

#Part 3 - Lab assignment
This is the last part of this lab. Will you make to the end?

1. First of we are going to set up unit testing and later integration testing for the database connection.
The `pom.xml` has already been updated with the required starter-POM for testing support. Check it out!
1. A new unit test has also been created which test the controller in the Spring Boot application. This relies on the MockMvc-class in
Spring Framework.
1. Change the URL to the controller in the unit test, so you see the JSON data returned from the previously created controller as output
when the test is executed. It should look something like this:
```
2015-02-27 13:07:20.217  INFO 9489 --- [           main] o.s.t.web.servlet.TestDispatcherServlet  : FrameworkServlet '': initialization completed in 11 ms
{"id":5,"data":"Hello IT-HUSET!"}
```
1. Great now you have the basic structure ready so you can perform unit tests. You are going to use it to test the database setup.
1. Now for the required dependencies for database access using Spring data JPA. The dependency to add to your `pom.xml` is:
```
spring-boot-starter-data-jpa
```
1. We also need a PostgreSQL-driver. Add this dependency:
```
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
    <version>9.3-1103-jdbc3</version>
</dependency>
```
1. Now under `src/main/resources`, add a file named `application.properties`. Add the following properties to it:
```
spring.datasource.url=jdbc:postgresql://ec2-54-243-229-57.compute-1.amazonaws.com:5432/<database>?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.max-active=2
spring.datasource.max-idle=2
spring.datasource.min-idle=1
spring.datasource.initial-size=1
spring.jpa.properties.hibernate.temp.use_jdbc_metadata_defaults=false
```
1. Remember that properties can be overridden on the command line. So after you packaged your app, you can for example
change the url by adding the parameter `--spring.datasource.url=something_else` when you start the application.
1. Ask your instructor for connection strings to add to the file above.
1. Remember the POJO you created. Lets turn that into an JPA entity.
1. Use the annotations below o the class. The properties should be named `id` and `data`.
```
@Entity //on the class
@Table( name="ithuset") //on the class
@Id //On the property named id.
```
1. Because of autoconfiguration, we now have a proper DataSource and a JdbcTemplate. But for this task we are using Spring data JPA
which also gives us a LocalContainerEntityManagerFactoryBean. Now time for some magic. At the following interface
to a package below `Application.class`:
  ```
  @Component
  public interface PojoRepository extends JpaRepository<YourPojoName, Integer> {
  }
  ```
1. Inject (with `@Autowired`) the interface in both the test class and the controller.
1. Use one of the find*-methods in the interface to retrieve data from the database in a new test case. When
you successfully managed to read database data, then move on to the last step. You can either at a test method in the
existing `MyControllerTest.java` or create a completely new test class.
1. In your controller, use the Repository to query the database and return the data either as a List or as a single object.
Compile and package your app, start it and navigate in a browser to the same URL as before. If you see the database data, then you
have successfully completed this lab!

Now have a beer and lean back. If you are want, you can checkout the branch "solution" to see my take of this.

Congratulations!!!
