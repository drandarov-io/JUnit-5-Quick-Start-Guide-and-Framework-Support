# JUnit 5 Quickstart Guide and Samples

### Set-Up
- IDE: I recommend using IntelliJ IDEA 2016.2+ since it brings native support to JUnit 5
- Dependencies:

Maven
```xml
      <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.0.0-M1</version>
            <scope>compile</scope>
      </dependency>
      <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-runner</artifactId>
            <version>1.0.0-M1</version>
            <scope>compile</scope>
      </dependency>
      <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <version>5.0.0-M1</version>
            <scope>runtime</scope>
      </dependency>
```
Gradle
```gradle
      dependencies {
          testCompile group: 'org.junit.jupiter', name: 'junit-jupiter-api', version: '5.0.0-M1'´
          testCompile group: 'org.junit.platform', name: 'junit-platform-runner', version: '1.0.0-M1'
          testRuntime group: 'org.junit.jupiter', name: 'junit-jupiter-engine', version: '5.0.0-M1'
      }
```

### Index
1. [JUnit5_00_GeneralChanges.java] (/src/test/java/com/drandarov/junit5/JUnit5_00_GeneralChanges.java)
    - Contains a collection of *small or* **general changes** made to the API

3. [JUnit5_01_NewFeatures.java] (/src/test/java/com/drandarov/junit5/JUnit5_01_NewFeatures.java)
    - Contains a collection of *smaller, general new features* like **lambda support** or **small annotations**
    
4. [JUnit5_02_NewAdvancedFeatures.java] (/src/test/java/com/drandarov/junit5/JUnit5_02_NewAdvancedFeatures.java)
    - Contains a collection of *more advanced, complicated or experimental new features* like the new **Extensions-API**
    and **Test-Parameters**. Also includes some **use-cases**.

### Tasks

- [x] Dependency Copy-Paste Resource          -   7/24/2016
- [x] Links to Java-Files                     -   7/24/2016
- [x] Reordered packages and classes          -   7/26/2016
- [x] @TestFactory + DynamicTests             -   7/26/2016
- [x] Test-Extensions (o\j\j\api\extension)   -   7/28/2016
- [x] Test-Extensions extended                -   7/29/2016
- [ ] Proper Presentation                     -
