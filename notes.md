**Question:** Why were records introduced?  
**Answer:** Records were introduced to reduce boilerplate for immutable data carriers. They automatically generate constructors,
accessors, equals(), hashCode(), and toString(). Unlike Lombok, records are a Java language feature, supported directly by the compiler,
and they enforce immutability by not providing setters.

**Question:** Why might you choose a record instead of a Lombok @Data class?  
**Answer:** I would use a Java record when I need an immutable data carrier. Records reduce boilerplate by automatically generating the constructor, accessors, equals(), hashCode(), and toString(). Unlike Lombok, records are a core Java language feature, so they don't require annotation processing or an additional dependency. This makes them ideal for DTOs, API request/response objects, configuration objects, and event payloads where the data should not change after creation.  



