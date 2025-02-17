# fray-gradle-examples


## Requirements

- Java 21

## Configuration 

```java
plugins {
    id("org.pastalab.fray.gradle") version "0.2.5"
}

```

## Write a concurrency test

- Writing concurrency tests is simple, and you can find examples in the `src/test/java` directory.
```java
public class TestClass {
  ...
    @ConcurrencyTest(
    )
    public void test() throws Exception {
      ...
    }
}
```

## Run the test

You can run the test through gradle task `./gradlew frayTest`, or launch it using your favorite IDE.
