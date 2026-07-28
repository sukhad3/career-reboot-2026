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


# Day 2

# Module 3 – Functional Programming
## Lesson 1–3 Notes

---

# Why Functional Programming?

Java introduced Functional Programming in Java 8 to make code:

- More concise
- More readable
- Easier to parallelize
- Easier to maintain

Traditional Java focuses on **how** to perform a task (imperative programming).

Functional programming focuses on **what** needs to be done.

Example:

Imperative

```java
int sum = 0;
for (int n : numbers) {
    sum += n;
}
```

Functional

```java
int sum = numbers.stream()
                 .mapToInt(Integer::intValue)
                 .sum();
```

The Stream API still uses loops internally, but hides the implementation from developers.

---

# Functional Programming Principles

## 1. Pure Functions

A pure function:

- Always produces the same output for the same input.
- Does not modify external state.
- Has no side effects.

Example:

```java
int add(int a, int b) {
    return a + b;
}
```

---

## 2. Immutability

Functional programming prefers immutable objects.

Instead of modifying an object, create a new one.

Benefits:

- Thread safety
- Predictable behavior
- Easier debugging

---

## 3. Side Effects

Examples of side effects:

- Printing
- Writing to a database
- Sending emails
- Modifying global variables
- Updating files

Functional code attempts to minimize side effects.

---

# Functional Interfaces

A Functional Interface contains exactly **one abstract method**.

Reason:

The compiler must know exactly which method a lambda expression implements.

Example:

```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
}
```

---

# Lambda Expressions

Instead of writing:

```java
Calculator calculator = new Calculator() {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
};
```

We can write:

```java
Calculator calculator = (a, b) -> a + b;
```

A lambda is a concise way to provide an implementation of a Functional Interface.

---

# JVM Internals

Anonymous Classes:

- Compiler generates additional class files.

Lambdas:

- Do NOT generate anonymous classes.
- Compiler generates an `invokedynamic` instruction.
- JVM creates the implementation at runtime using `LambdaMetafactory`.

Interview takeaway:

> Lambdas are implemented using the JVM's `invokedynamic` mechanism rather than anonymous inner classes.

---

# Built-in Functional Interfaces

## Predicate<T>

Purpose:

Returns a boolean.

```java
Predicate<Employee> highSalary =
        employee -> employee.salary() > 100000;
```

Common Stream operation:

```java
filter()
```

---

## Function<T, R>

Purpose:

Transforms one object into another.

```java
Function<Employee, String> employeeName =
        Employee::name;
```

Common Stream operation:

```java
map()
```

---

## Consumer<T>

Purpose:

Consumes a value and returns nothing.

```java
Consumer<Employee> printer =
        System.out::println;
```

Common Stream operation:

```java
forEach()
```

---

## Supplier<T>

Purpose:

Produces an object without taking any input.

```java
Supplier<UUID> uuid =
        UUID::randomUUID;
```

---

## UnaryOperator<T>

Purpose:

Input and output are the same type.

```java
UnaryOperator<String> upper =
        String::toUpperCase;
```

---

## BinaryOperator<T>

Purpose:

Accepts two values of the same type and returns one value.

```java
BinaryOperator<Integer> add =
        Integer::sum;
```

Common Stream operation:

```java
reduce()
```

---

# Functional Interface Summary

| Requirement | Interface |
|-------------|-----------|
| Return true/false | Predicate |
| Transform object | Function |
| Perform action | Consumer |
| Produce object | Supplier |
| Same type in/out | UnaryOperator |
| Combine two values | BinaryOperator |

---

# Deferred Execution

A lambda expression defines behavior but does not execute immediately.

Example:

```java
Predicate<Employee> highSalary =
        employee -> employee.salary() > 100000;
```

Nothing happens until:

```java
highSalary.test(employee);
```

---

# Stream Laziness

Streams are **lazy**.

Intermediate operations only build the pipeline.

Example:

```java
employees.stream()
         .filter(...)
         .map(...);
```

Nothing executes yet.

Execution starts only after a terminal operation.

Example:

```java
employees.stream()
         .filter(...)
         .map(...)
         .toList();
```

`toList()` triggers execution.

---

# Intermediate Operations

- filter()
- map()
- flatMap()
- sorted()
- distinct()
- peek()
- limit()
- skip()

These return another Stream.

---

# Terminal Operations

- toList()
- collect()
- forEach()
- count()
- reduce()
- findFirst()
- anyMatch()
- allMatch()
- min()
- max()

These execute the pipeline.

---

# Interview Questions

### Why must a Functional Interface contain exactly one abstract method?

Because the compiler must know exactly which method a lambda expression implements.

---

### Why are Streams lazy?

Streams delay execution until a terminal operation is called. This enables pipeline optimization, avoids unnecessary work, reduces intermediate object creation, and improves performance.

---

# Key Takeaways

- Functional programming focuses on **what** rather than **how**.
- Streams abstract iteration; loops still exist internally.
- Lambda expressions implement Functional Interfaces.
- Functional Interfaces have exactly one abstract method.
- Lambdas use `invokedynamic`, not anonymous inner classes.
- Know the six built-in Functional Interfaces.
- Streams are lazy.
- Intermediate operations build a pipeline.
- Terminal operations trigger execution.