# jOntology

A lightweight Java library that provides marker interfaces for classifying objects by their fundamental nature — mutability, statefulness, and purpose.

## Why

In large codebases, the *intent* behind a class often gets lost. Is this object safe to cache? Can it be shared across threads? Is its identity defined by its fields or by a reference? These questions are usually answered by reading the implementation and hoping the author was consistent.

jOntology makes these design decisions explicit and machine-readable by encoding them as marker interfaces. This enables:

- **Self-documenting code** — a class declaration tells you its contract at a glance.
- **Compile-time guardrails** — generic bounds can restrict parameters to `Immutable`, `Stateless`, etc.
- **Static analysis and annotation processing** — tooling can verify that a class marked `Immutable` doesn't expose setters or mutable fields.

## Type Hierarchy

```
Mutable
Immutable
├── Stateless
├── ValueObject (sealed)
│   ├── Scalar
│   └── Vector<S extends Scalar>
└── FunctionalObject
    └── StatelessFunctionalObject
```

### Mutable

An object whose state can change after construction. Marking a class as `Mutable` signals that callers must account for concurrent access and should not assume repeatability of method results.

### Immutable

An object whose observable state never changes after construction. Immutable objects are inherently thread-safe and safe to cache or share freely.

### Stateless

An `Immutable` object that holds no state at all. All instances are functionally identical and interchangeable — it exists purely to provide behavior.

### ValueObject

An `Immutable` object whose identity is determined entirely by the values it holds. Two value objects with the same fields are considered equal. `equals` and `hashCode` should reflect this.

`ValueObject` is sealed: a value object is a `Scalar` or a `Vector`, nothing else.

### Scalar

A `ValueObject` that is one value, not a sequence: a sum (a sealed interface), a product (a record) or an enum declared by the application. Its fields are primitives, scalars or vectors.

### Vector

A `ValueObject` that is a finite sequence of one `Scalar` type, equal by its elements in order. `Vector<S extends Scalar>` is an interface with no methods and no implementation; the application supplies one and keeps it immutable. The bound rules out vectors of primitives and vectors of vectors — wrap the element in a one-component record instead.

### FunctionalObject

An `Immutable` object that groups a set of related functions. It may hold immutable configuration state that parameterizes its behavior, or it may be fully stateless.

### StatelessFunctionalObject

A `FunctionalObject` that is also `Stateless` — pure functions with no configuration. All instances are interchangeable.

## Usage

```java
// A thread-safe, cacheable configuration holder
public record DatabaseConfig(String host, int port) implements Scalar {}

// A sum: a sealed scalar whose variants are scalars
public sealed interface Shape extends Scalar permits Circle, Square {}
public record Circle(long radius) implements Shape {}
public record Square(long side) implements Shape {}

// A sequence of scalars; the application keeps it immutable
public final class Shapes implements Vector<Shape> {
    private final Shape[] elements;
    // copies on construction; equals/hashCode by elements in order
}

// A pure function group — any instance will do
public final class JsonParser implements StatelessFunctionalObject {
    public Document parse(String raw) { /* ... */ }
}

// A parameterized function group
public final class RetryPolicy implements FunctionalObject {
    private final int maxAttempts;
    public <T> T execute(Supplier<T> action) { /* ... */ }
}

// An entity with changing state
public class ShoppingCart implements Mutable {
    private final List<Item> items = new ArrayList<>();
    public void add(Item item) { items.add(item); }
}
```

## Modules

| Module | Description |
|--------|-------------|
| **core** | The marker interfaces (`Immutable`, `Mutable`, `Stateless`, `ValueObject`, `Scalar`, `Vector`, `FunctionalObject`, `StatelessFunctionalObject`) |
| **enforcer** | Runtime contract verification — checks that classes honor the rules implied by their marker interfaces (e.g. `Immutable` fields are final, `Stateless` has no fields). A `ValueObject` is checked as an `Immutable`; its value-object rules are the application's to enforce |
| **enforcing-utils** | CLI tool that scans an entire package, runs the enforcer on every class, and prints a summary report |
| **test-fixtures** | Shared fixture classes used by both `enforcer` and `enforcing-utils` tests |

## Installation

Add the dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>io.github.captainssingapura-hue.lang.ontology</groupId>
    <artifactId>core</artifactId>
    <version>0.0.1</version>
</dependency>
```

To also use the runtime enforcer:

```xml
<dependency>
    <groupId>io.github.captainssingapura-hue.lang.ontology</groupId>
    <artifactId>enforcer</artifactId>
    <version>0.0.1</version>
</dependency>
```

To scan and enforce an entire package:

```xml
<dependency>
    <groupId>io.github.captainssingapura-hue.lang.ontology</groupId>
    <artifactId>enforcing-utils</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Requirements

- Java 21+

## Build

```
mvn clean install
```

## License

MIT
