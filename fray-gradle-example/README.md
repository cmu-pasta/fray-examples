# fray-gradle-examples


## Requirements

- Java 21

## Configuration 

- Currently Fray is in Alpha release and only published in Github maven repository. 
To use it, you need to add the following repository to your `build.gradle.kts` and `settings.gradle.kts` file.


- `build.gradle.kts`
```kotlin
repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/cmu-pasta/fray")
        credentials {
            username = extra["gpr.user"] as String? ?: System.getenv("USERNAME")
            password = extra["gpr.key"] as String? ?: System.getenv("TOKEN")
        }
    }
}
```

- Github credentials are required to access the repository. 
You can create a personal access token from [Github](https://github.com/settings/tokens/new) and with 
  `read:packages` permission selected. 
- You may set your Github username and token in the `$HOME/.gradle/gradle.properties` file as follows:
```properties
gpr.user=your_github_username
gpr.key=your_github_token
```

- Or through environment variables:
```shell
export USERNAME=your_github_username
export TOKEN=your_github_token
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
