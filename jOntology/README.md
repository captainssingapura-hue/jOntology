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
├── ValueObject
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

### FunctionalObject

An `Immutable` object that groups a set of related functions. It may hold immutable configuration state that parameterizes its behavior, or it may be fully stateless.

### StatelessFunctionalObject

A `FunctionalObject` that is also `Stateless` — pure functions with no configuration. All instances are interchangeable.

## Usage

```java
// A thread-safe, cacheable configuration holder
public final class DatabaseConfig implements ValueObject {
    private final String host;
    private final int port;
    // ...
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
| **core** | The marker interfaces (`Immutable`, `Mutable`, `Stateless`, `ValueObject`, `FunctionalObject`, `StatelessFunctionalObject`) |
| **enforcer** | Runtime contract verification — checks that classes honor the rules implied by their marker interfaces (e.g. `Immutable` fields are final, `ValueObject` overrides `equals`/`hashCode`) |
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
