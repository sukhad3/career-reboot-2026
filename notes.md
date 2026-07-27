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



