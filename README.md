# JUnit 5 QuickStart-Guide

### Set-Up
- IDE: I recommend using <b>IntelliJ IDEA 2016.2+</b> since it brings native support to JUnit 5
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
1. [JUnit5_NameChanges.java] (/src/test/java/com/drandard/changes/JUnit5_NameChanges.java)<br>
    - Contains a list of <u>changed names</u> of Annotations, Classes, etc.

2. [JUnit5_GeneralChanges.java] (/src/test/java/com/drandard/changes/JUnit5_GeneralChanges.java)
    - Contains a collection of <u>small or general changes</u> made to the API
    
3. [JUnit5_AdvancedChanges.java] (/src/test/java/com/drandard/changes/JUnit5_AdvancedChanges.java)
    - Contains several <u>advanced changes</u> made to the API
    
4. [JUnit5_NewGeneralFeatures.java] (/src/test/java/com/drandard/features/JUnit5_NewGeneralFeatures.java)
    - Contains a collection of <u>small, general new features</u>
    
4. [JUnit5_NewAdvancedFeatures.java] (/src/test/java/com/drandard/features/JUnit5_NewAdvancedFeatures.java)
    - Contains a collection of <u>bigger, more advanced new features</u>

### TODO
- [x] Dependency Copy-Paste Resource
- [x] Links to Java-Files
- [ ] Proper Presentation
