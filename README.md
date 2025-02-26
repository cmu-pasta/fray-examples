# Fray examples

Sample "hello world" programs for running Fray on a bank account example with an atomicity violation.

## Gradle example

```
cd fray-gradle-example
./gradlew build -x test
./gradlew frayTest
```

## CLI example

First, build it:
```
cd fray-cli-example
javac BankAccount.java
```

Then run:
```
cd /path/to/fray
./bin/fray -cp /path/to/fray-cli-example BankAccount
```
