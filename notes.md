**Question:** Why were records introduced?  
**Answer:** Records were introduced to reduce boilerplate for immutable data carriers. They automatically generate constructors,
accessors, equals(), hashCode(), and toString(). Unlike Lombok, records are a Java language feature, supported directly by the compiler,
and they enforce immutability by not providing setters.

**Question:** Why might you choose a record instead of a Lombok @Data class?  
**Answer:** I would use a Java record when I need an immutable data carrier. Records reduce boilerplate by automatically generating the constructor, accessors, equals(), hashCode(), and toString(). Unlike Lombok, records are a core Java language feature, so they don't require annotation processing or an additional dependency. This makes them ideal for DTOs, API request/response objects, configuration objects, and event payloads where the data should not change after creation.  

Why are sealed classes useful?

A strong answer would be:
They allow you to define a closed hierarchy of types. The compiler knows all valid subclasses, enabling exhaustive switch expressions, stronger compile-time checking, and making the code easier to maintain. They're particularly useful when the domain naturally has a fixed set of implementations.

Sealed classes define a closed set of valid implementations. Pattern matching in a switch uses that knowledge to verify at compile time that every possible type is handled. This allows exhaustive switches without a default case and catches missing business cases when the hierarchy changes.

# Day 1 - Modern Java Fundamentals

## Topics Covered

- Records
- Sealed Classes
- Pattern Matching
- Text Blocks
- Virtual Threads
- Git Rebase
- Spring Boot Project Structure

---

# 1. Records

## Why were Records introduced?

Records reduce boilerplate for immutable data carrier classes.

Instead of manually writing:

- Constructor
- Getters
- equals()
- hashCode()
- toString()

the compiler generates them automatically.

Records are best suited for:

- DTOs
- API Requests
- API Responses
- Configuration objects
- Events

They are NOT generally used as JPA Entities.

---

## Example

```java
public record Employee(
        long id,
        String name,
        String department,
        double salary
) {}
```

Usage

```java
Employee employee =
        new Employee(1, "Sukhdev", "Engineering", 150000);

System.out.println(employee.name());
System.out.println(employee);
```

---

## Key Points

✔ Immutable

✔ Generates:

- Constructor
- Accessor methods
- equals()
- hashCode()
- toString()

❌ No setters

❌ Accessors are NOT JavaBean getters.

Example

```java
employee.name();
```

NOT

```java
employee.getName();
```

---

## Interview Questions

Q: Why use a Record instead of Lombok @Data?

Answer:

- Built into Java
- No third-party dependency
- Immutable
- Less boilerplate
- Compiler support

---

Q: Would you use Records as JPA Entities?

Answer:

Generally no.

JPA entities are mutable and rely on ORM features such as proxies and lifecycle management. Records are better suited for immutable DTOs.

---

# 2. Sealed Classes

## Why were they introduced?

To model a closed hierarchy.

Example:

Only these employee types are valid.

- Developer
- Manager
- Intern
- Contractor

No other implementation is allowed.

---

Example

```java
public sealed interface EmployeeType
        permits Developer,
                Manager,
                Intern,
                Contractor {
}
```

---

Implementations

```java
public record Developer(String language)
        implements EmployeeType {
}

public record Manager(int teamSize)
        implements EmployeeType {
}

public record Intern(String mentor)
        implements EmployeeType {
}

public record Contractor(
        String company,
        double hourlyRate)
        implements EmployeeType {
}
```

---

## Benefits

- Better domain modeling
- Compiler knows all implementations
- Safer code
- Easier maintenance
- Enables exhaustive switch expressions

---

## Interview Question

Why use Sealed Classes?

Answer:

They define a closed hierarchy allowing the compiler to verify all valid implementations.

---

# 3. Pattern Matching

Old Java

```java
if(employee instanceof Developer){

    Developer d = (Developer) employee;

}
```

Modern Java

```java
if(employee instanceof Developer developer){

}
```

No explicit cast required.

---

Pattern Matching with switch

```java
return switch(employee){

    case Developer developer ->
            "Developer : "
            + developer.language();

    case Manager manager ->
            "Manager : "
            + manager.teamSize();

    case Intern intern ->
            "Intern : "
            + intern.mentor();

    case Contractor contractor ->
            "Contractor : "
            + contractor.company();
};
```

---

## Why Sealed Classes + Pattern Matching work together

Sealed classes define the complete list of implementations.

Pattern matching allows the compiler to verify every case has been handled.

Result:

No default case required.

---

# 4. Text Blocks

Before

```java
String json =
"{\n" +
"\"name\":\"Sukhdev\"";
```

After

```java
String json = """
{
    "name":"Sukhdev",
    "department":"Engineering"
}
""";
```

---

Benefits

- Better readability
- Less escaping
- Ideal for

- JSON
- XML
- SQL
- HTML
- Test Data

---

# 5. Virtual Threads

Traditional Thread

```
1 Request
      ↓
1 Platform Thread
```

Virtual Threads

```
10000 Requests
        ↓
10000 Virtual Threads
        ↓
Small Pool of Platform Threads
```

---

Best suited for

✔ REST APIs

✔ Database Calls

✔ HTTP Clients

✔ File I/O

✔ Message Queues

---

Not ideal for

- Image Processing
- Video Encoding
- AI Model Inference
- Heavy Mathematical Computation

Reason:

Virtual Threads help when a task waits.

CPU intensive tasks rarely wait.

---

Example

```java
try(var executor =
        Executors.newVirtualThreadPerTaskExecutor()){

    executor.submit(() -> {

        Thread.sleep(1000);

        return null;
    });

}
```

---

Interview Question

Would you use Virtual Threads for CPU intensive work?

Answer:

Generally no.

Virtual Threads provide the biggest benefit for I/O-bound workloads where threads spend significant time waiting. CPU-bound tasks keep the processor busy, so Virtual Threads do not significantly improve throughput.

---

# 6. Spring Boot Project Structure

```
src
 ├── main
 │    ├── java
 │    └── resources
 └── test
      └── java
```

Important lesson learned:

Only code inside

```
src/main/java
```

is compiled.

Creating files under

```
src/main/com
```

results in classpath errors.

---

# 7. Git

Useful Commands

```bash
git status
git log --oneline --graph --decorate --all
git pull --rebase
git push
```

---

Why rebase?

Keeps commit history linear and easier to read.

---

# Key Takeaways

- Prefer immutable data where possible.
- Use Records for DTOs.
- Use JPA Entities for persistence.
- Use Sealed Classes when the domain has a fixed set of implementations.
- Pattern Matching removes unnecessary casting.
- Virtual Threads improve scalability for I/O-bound applications.
- Return values from methods instead of printing directly to improve testability and separation of concerns.
- Keep business logic separate from presentation.
- Understand not only how a feature works, but also the problem it solves.

---

# Personal Notes

(Add your own observations here.)

-
-
-

# Questions to Revisit

-
-
-


