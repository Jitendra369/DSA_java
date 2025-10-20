# general preparation

- links 
- [﻿sand-tray-c9b.notion.site/Tricky-Oops-questions-asked-in-Technical-Interview-1e262faa621c80d789f4f8e5a5766f06](https://sand-tray-c9b.notion.site/Tricky-Oops-questions-asked-in-Technical-Interview-1e262faa621c80d789f4f8e5a5766f06) 
- IOC
 context - cache from where bean reside 
    - classpath / annotation base / xml based config 
    - BeanFactory > ApplicationContext



- setter injection
Setter Injection = Spring calls your setter method (setXyz(...)) after the bean is created.
It’s best used for optional, late-loaded, or configurable dependenciescod
code

```
public interface MessageService {    void sendMessage(String message);}

-- email service 
@Component("emailService")
public class EmailService implements MessageService {
    @Override
    public void sendMessage(String message) {
        System.out.println("sending email ");
    }
}

// sms service 
@Component("smsService")
public class SMSService implements MessageService {

    @Override
    public void sendMessage(String message) {
        System.out.println("sending message ");
    }
}

// set the component at runtime 
@Service
public class NotificationManager {

    private MessageService messageService;

    public void sendNotification(String message){
        messageService.sendMessage(message);
    }

    public void changeMessageService(MessageService messageService){
        this.messageService = messageService;
    }
}

// main class code , based on the type of messageservice we can use setter injection 
if (sendMessageService){
	MessageService smsService = applicationContext.getBean("smsService", MessageService.class);
	smsService.sendMessage("this is message service ");
}else{
	MessageService emailService = applicationContext.getBean("emailService", MessageService.class);
	emailService.sendMessage("this is email service ");
}
```
### **Simple way to remember:**
- **Constructor** = _"I __**need**__ this to exist."_
- **Setter** = _"You can __**give**__ me this later if needed."_
usage of componentScan 

```
@ComponentScan(basePackages = {
    "com.example.service",
    "com.example.repository",
    "com.external.lib"
})
```
**you can exclude the packages **

```
@ComponentScan(
    basePackages = "com.example",
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)
)
```
**another way of using the preContruct and postDestory **

```
public class ConfigurationService {

    public void init(){
        System.out.println("this method is called when the bean is created ");
    }

    public void destory(){
        System.out.println("this method is called before bean is going to destory ");
    }

    public void printMessage(){
        System.out.println("printing the message .....");
    }
}
```
**here , we specify the init() and destory() method **

```
@Configuration
public class AppConfig {


    **@Bean(initMethod = "init", destroyMethod = "destory") // here is the code **
    public ConfigurationService getConfig(){
        return new ConfigurationService();
    }
}
```
**usage of **`**@ConditionalOnExpression**` 

suppose we have 2 key in properties file

```
app.scheduler.enable=true
eapp.scheduler.prod=true
```
_**and we have inject the bean , if the both value in the property file is true , in that case we can use this annotation**_ 

```
﻿@Configuration
@ConditionalOnExpression("${**app.scheduler.enable:true** and **app.scheduler.prod:true**")
public class SchedulerConfig {

    @Bean
    public Scheduler schedulerService(){
        return new Scheduler();
    }
} 
```
- what is default size of the arraylist :
> Since Java **8**, the default constructor **does not allocate any array immediately**.
The internal array (`elementData` ) is set to a shared **empty array** (`DEFAULTCAPACITY_EMPTY_ELEMENTDATA` ).
Only when you **add the first element**, it initializes the array with a **default capacity of 10**.
you can also set the size for that arrayList 


- _**How you consume rest APIs from your application ?**_
    - In our project we use FeignClient for calling the web-service , it more of declerative apporach.
    - we generally have 3 approach 
        - RestTemplate 


```java
String url = "https://jsonplaceholder.typicode.com/users/1";
ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
```
- WebClient : [ reactive way ] 
```java
private final WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");
public String getUser() {
        return webClient.get()
                .uri("/users/1")
                .retrieve()
                .bodyToMono(String.class)
                .block(); // convert reactive Mono<String> to actual String
```
- FeignClient : [ spring cloud OpenFeign ] more clearner way 
```java
@FeignClient(name = "userClient", url = "https://jsonplaceholder.typicode.com")
public interface UserClient {

    @GetMapping("/users/1")
    String getUser();
} 
```


![image.png](https://eraser.imgix.net/workspaces/4BjU8F3Hl3RA0vlyYZEr/s5qOyms8b6anZCYXiYyyoV6Bzz32/image_Gg7mnKHrctr8juLM1YjmE.png?ixlib=js-3.7.0 "image.png")

how to configure various profile in springboot application ?

> 
we can configure various properties according the environment , like if we have 3 env like INT , UAT and PPR , then we can create the 3 properties file ,
application-dev.properties
application-uat.properties
application-ppr.properties
and we can enable then , using spring.profiles.active=dev

enable using CLI 

```
java -jar myapp.jar --spring.profiles.active=prod
```
As an environment variable 

```java
export SPRING_PROFILES_ACTIVE=test
```
we can also use java class to read the properties file from properties file .

```java
@Component@ConfigurationProperties(prefix = "app") 
public class AppConfiguration {    
private Scheduler scheduler = new Scheduler();
    public Scheduler getScheduler() {
              return scheduler;    
             }  
              
    public void setScheduler(Scheduler scheduler) {
             this.scheduler = scheduler;    
        }
  }
```
_** now u can inject this class , and use scheduler properties **_

## Externalize Configurations
You can keep config outside the JAR — so you don’t rebuild for every environment.

Run app like this:

```java
java -jar myapp.jar --spring.config.location=/opt/configs/
```
or use

```java
--spring.config.additional-location=...
```
- start the springboot application using CLI
    - user should have maven install in our PC 
    - command : `**mvn spring:boot:run **` 



what are the advantage of** open-feign-client **over **restTemplate ?**

![image.png](https://eraser.imgix.net/workspaces/4BjU8F3Hl3RA0vlyYZEr/s5qOyms8b6anZCYXiYyyoV6Bzz32/image_EZdqvplmML6ScQvqhcgoR.png?ixlib=js-3.7.0 "image.png")

- if we have same method persent in the parent and child class , and if the method access modifier is private , then child class cannot inherite the method from the parent class.
> This is **not overriding**. The show() method in Parent is private, so it is **not inherited** by Child. The Child class is **defining a new method**, not overriding. It’s called **method '**

- _**Can sub-class access the method from parent class , if the constructor is define as protected ?**_
> Yes, this is allowed. 
protected
 gives access to **subclasses**, even in **different packages**. But access to protected constructors still requires using 
super()
 **explicitly** in the subclass.

### **Can a **`**final**`** Method Be Overridden Indirectly?**
**Question**: Can you override a method that is declared `final` in the parent class indirectly through an interface default method?

```java
java

`﻿`interface I {
    default void show() {
        System.out.println("Interface show");
    }
}

class A {
    public final void show() {
        System.out.println("A's final show");
    }
}

class B extends A implements I {
    // No override possible
}
```
**Answer**: You **cannot override** `show()` in `B`, even though the interface provides a `default` method. The `final` method in class `A` takes precedence. Compiler will throw an error if you try to override it.

- to clone the object we have clonable interface , and clone overide mehthod 
```
protected Object clone() throws CloneNotSupportedException 
```
but this is shallowed copying , if some object is present inside the object and it has variables , then it just refere to the same object , for that case we need to use deep-copying.

```
@Override
 protected Object clone() throws CloneNotSupportedException {
  Person clonePerson = new Person();
  clonePerson.setName(this.getName());
 
  Address cloneAddress = new Address();
  cloneAddress.setPinCode(this.getAddress().getPinCode());
  cloneAddress.setCity(this.getAddress().getCity());
 
  clonePerson.setAddress(cloneAddress);
  return clonePerson;
 }
```
final  class

### 1. `final` means **the reference cannot be reassigned**,
but the **object itself can still change**.

to make the collection immutable , so no-one can change > add/ remove the collection.

```java
public final class Employee {
  private final String name;
  private final String address;
  private final List<String> mobileNumbers;
 
  public Employee(String name , String address, List<String> mobileNumbers) {
  this.name = name;
  this.address = address;
  this.mobileNumbers = List.copyOf(mobileNumbers);
  }
```
**OOPS concept : **

![image.png](https://eraser.imgix.net/workspaces/4BjU8F3Hl3RA0vlyYZEr/s5qOyms8b6anZCYXiYyyoV6Bzz32/image_p-a71c3MWQrOYJdhtn7Sw.png?ixlib=js-3.7.0 "image.png")

- _**what if we use @Controller over the @RestController ?**_
```java
@Controller
@RequestMapping("/test/hello")
public class TestController {

    @GetMapping()
    public String getData(){
        return "Payment successful";
    }
} 
```
exception **Whitelabel Error Page**

> Spring will **NOT** return `"Payment successful"` as text.
Instead, it will interpret it as a **view name**.
Spring will now try to find a **template or JSP** file named:


`@RestController` is made up of 

`@Controller
@ResponseBody ` 

what is @SpringbootApplication annotation ?

is equivalent to writing:

- **@Configuration**
    - `Marks the class as a **source of bean definitions** for the Spring container.It’s like saying, “this class contains configuration methods for Spring.”

- **@EnableAutoConfiguration**
    - This is the **heart of Spring Boot**.It tells Spring Boot to **automatically configure** your application based on the dependencies in your `classpath` .
    - 🧠 Internally, it uses `spring.factories`  to load configurations dynamically from many auto-configuration classes.
    - If Spring sees `spring-boot-starter-web`  → it auto-configures Tomcat, DispatcherServlet, etc.If it sees `spring-boot-starter-data-jpa`   → it auto-configures EntityManager, DataSource, Hibernate, etc.

- **@ComponentScan **
    - Enables **component scanning** in the package where the main class resides (and subpackages).It tells Spring to look for classes annotated with:
        - `@Component` `@Service`  `@Repository`  `@Controller`  `@RestController` and automatically register them as beans in the application context.  




