# fray-gradle-examples


## Requirements

- Java 21

## Add Fray Repo to Maven 

- Currently Fray is in Alpha release and only published in Github maven repository. 
To use it, you need to add `https://maven.pkg.github.com/cmu-pasta/fray` to your maven repository.

Please follow the official [guidance](https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-apache-maven-registry).


## Configure your Maven project

- First please add Fray plugin to your project 

```
<plugin>
    <groupId>org.pastalab.fray.maven</groupId>
    <artifactId>fray-maven-plugin</artifactId>
    <version>0.1.2</version>
    <executions>
        <execution>
            <id>prepare-fray</id>
            <goals>
                <goal>prepare-fray</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

- Next, please add the `fray-junit` dependency
 
```
<dependency>
    <groupId>org.pastalab.fray</groupId>
    <artifactId>junit</artifactId>
    <version>0.1.1</version>
    <scope>test</scope>
</dependency>
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

You can run the test through gradle task `mvn verify`. Unfortunately, due to the constraints of Maven 
and intellij, you cannot launch Fray tests using Maven directly. 
