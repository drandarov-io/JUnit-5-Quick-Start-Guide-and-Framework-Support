# JUnit 5 QuickStart-Guide

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
    - Contains a collection of <u>small or general changes</u> made to the API

3. [JUnit5_01_NewFeatures.java] (/src/test/java/com/drandarov/junit5/JUnit5_01_NewFeatures.java)
    - Contains a collection of <u>smaller, general new features</u> like <b>lambda support</b> or <b>small annotations</b>
    
4. [JUnit5_02_NewAdvancedFeatures.java] (/src/test/java/com/drandarov/junit5/JUnit5_02_NewAdvancedFeatures.java)
    - Contains a collection of <u>more advanced, complicated or experimental new features</u> like the new <b>Extensions-API</b> and <b>Test-Parameters</b>

### Tasks

- [x] Dependency Copy-Paste Resource          -   7/24/2016
- [x] Links to Java-Files                     -   7/24/2016
- [x] Reordered packages and classes          -   7/26/2016
- [x] @TestFactory + DynamicTests             -   7/26/2016
- [x] Test-Extensions (o\j\j\api\extension)   -   7/28/2016
- [ ] Proper Presentation                     -
