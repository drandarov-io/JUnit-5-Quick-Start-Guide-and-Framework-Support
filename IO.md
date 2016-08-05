JUnit 5 Quickstart Guide and Samples
====================================

Table of contents
-----------------

- [JUnit 5 Quickstart Guide and Samples](#)
    - [Introduction](#introduction)
        - [Information](#Information)
        - [Set-Up](#set-up)
    - [General changes](#general-changes-code)
        - [Syntax](#syntax)
        - [Naming](#naming)
    - [New features: Basics](#new-features-basics-code)
        - [General](#general)
        - [Assertions and Lambda-Support](#assertions-and-lambda-support)
        - [Parameter Resolver](#parameter-resolver)
    - [New features: Advanced](#new-features-advanced-code)
        - [Test-Factories](#test-factories)
        - [Test-Extensions](#test-extensions)
        - [Test-Parameters](#test-parameters)
    - [Advanced Test-Samples](#advanced-test-samples-code)
        - [Extended disabled weekdays](#extended-disabled-weekdays)
        - [Extend Test-Annotation](#extend-test-annotation)
        - [Benchmarking Example](#benchmarking-example)
    - [Closing words](#closing-words)
        - [Contribution](#contribution)
        - [Further Reference](#further-reference)


Introduction
------------

### Information
Some headers related to code will have a code-link behind their name directing to the corresponding class in the
GitHub-Reposiory.

For the whole source code see my GitHub-Repository:  
[**dmitrij-drandarov/JUnit-5-QuickStart-Guide-and-Samples**
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced)

### Set-Up
For an IDE I recommend *IntelliJ IDEA 2016.2(+)* right now since it brings native support for JUnit 5.  
As for dependencies:

*Maven*
```xml
  <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.0.0-M2</version>
        <scope>compile</scope>
  </dependency>
```
```xml
  <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-runner</artifactId>
        <version>1.0.0-M2</version>
        <scope>compile</scope>
  </dependency>
```
```xml
  <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.0.0-M2</version>
        <scope>runtime</scope>
  </dependency>
```

*Gradle*
```gradle
  testCompile group: 'org.junit.jupiter', name: 'junit-jupiter-api', version: '5.0.0-M2'
```
```gradle
  testCompile group: 'org.junit.platform', name: 'junit-platform-runner', version: '1.0.0-M2'
```
```gradle
  testRuntime group: 'org.junit.jupiter', name: 'junit-jupiter-engine', version: '5.0.0-M2'
```


General changes [(code)](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/src/test/java/com/drandarov/junit5/JUnit5_00_GeneralChanges.java)
------------------------

### Syntax
This paragraph contains the small or general changes made in the transition from JUnit 4 to JUnit 5. Those are simple
but still note worthy.

The first change is made to the most basic of things: the test and the `@Test`-annotation themselves. You no longer 
need to make the test `public`, however you can still not make it `static` or `private`. Also timeout and expected 
parameter functionality has moved elsewhere.

![img/00_simple_test.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/00_simple_test.png?raw=true)

### Naming

Other annotations have received slight changes as well, including the common `@BeforeClass`, `@BeforeEach`, their
`@After...` aequivalents, `@Ignored` and the lesser known `@Category`. All of these have been renamed and given the
same treatment regarding `public` as `@Test`.

![img/01_other_annotations.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/01_other_annotations.png?raw=true)

`Assert` and `Assume` classes have been renamed as well and are now called `Assertions` and `Assumptions`. Not much has
changed for the naming of the methods of both classes.

![img/02_assertions_assumptions.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/02_assertions_assumptions.png?raw=true)


New features: Basics [(code)](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/src/test/java/com/drandarov/junit5/JUnit5_01_NewFeaturesBasics.java)
-----------------------------

### General
Here I want to introduce some basics for the new features available in the new version.  
There is a new pretty annotation called `@DisplayName` which is supposed to improve the readability of test reports, so
you don't need 40-character test-names to make clear what the test is about at a glance.

![img/03_displayname.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/03_displayname.png?raw=true)

![img/04_displayname_result.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/04_displayname_result.png?raw=true)

You can now also group tests with inner classes annotated with `@Nested`.

![img/05_nestedTests.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/05_nestedTests.png?raw=true)

![img/06_nestedTests_result.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/06_nestedTests_result.png?raw=true)

### Assertions and Lambda-Support
Now for the probably most known and anticipated feature in JUnit 5: Lambda-Support...  
JUnit 5 `Assertions` and `Assumptions` classes and its methods now provide Lambda support. This is achieved by providing
methods with functional interfaces as parameters.

The most used ones are the `BooleanSupplier` and `Supplier<String>`. The first one is used for assertions and the latter
one to provide a result-message. Those are however just alternatives to the older plain `boolean` and `String`.  
Assertion methods like `assertTrue(...)` are now just overloaded with combinations of those four parameters:  
(`boolean` | `BooleanSupplier`) & (`String` | `Supplier<String>`) resulting in 4 different methods. This is what most
lambda-supporting methods are designed like.

![img/05_assertSupplier.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/07_assertSupplier.png?raw=true)

A new important functional interface is `Executable`. It is very similar to a `Runnable`, however it throws a
`Throwable` meaning you can execute assertions like `assertTrue()` and an `AssertionError` may be thrown affecting your
test-result. It is used in several assertions like the new `assertAll(Executable... executables)` which can be also used
to prevent repitition.

![img/07_assertAll.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/08_assertAll.png?raw=true)

This new functional interface is also used in the new replacement of the old `@Test`-parameter `expected` which is
called `assertThrows()`. It asserts whether an exception was thrown.
If you need the exception-instance itself to e.g. assert the message, you can instead use `expectThrows()` which also
has the exception as return type.

![img/06_assertThrows.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/09_assertThrows.png?raw=true)

![img/07_assertAll.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/10_expectThrows.png?raw=true)

### Parameter Resolver

The biggest new feature in JUnit 5 is the new Extension-API. A part of it is the `ParameterResolver`-Interface which is
an extension of the `Extension`-Interface itself. The `ParameterResolver`-Interface provide a way for dependency
injection on method level by injecting data into test-method parameters.  
JUnit 5 provides two implementations by itself: `TestInfo` which contains some meta information and the appropriate
Test-`Method` and Test-`Class` instances and `TestReporter` which can be used to publish test entries.  
A lot more on the Extension-Api is following further below.

![img/08_parameterResolver.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/11_parameterResolver.png?raw=true)


New features: Advanced [(code)](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/src/test/java/com/drandarov/junit5/JUnit5_02_NewFeaturesAdvanced.java)
-------------------------------

### Test-Parameters
Building upon the `ParameterResolver` paragraph of the last chapter let's look at implementing your own
`ParameterResolver`. You can also see the first visual sign of the Extension-API in the form of the
`@ExtendWith`-Annotation. The final result is:

![img/12_parameterResolverExt.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/12_parameterResolverExt.png?raw=true)

This is achieved by the following implementations:

The first implementation processes the `String` parameter `className`. It checks whether the parameter class is a
`String` and throws an exception otherwise. To resolve and inject the parameter it just returns the test classes name.

![img/13_parameterClassName.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/13_parameterClassName.png?raw=true)

The seconds implementation processes the `Long` parameter `parameterIndex`. It does basically the same but resolves the
parameter by getting the index from the `parameterContext`.

![img/14_parameterIndex.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/14_parameterIndex.png?raw=true)

### Test-Factories
Another big feature are the new Test-Factories. These are annotated with `@TestFactory` instead of `@Test`. Their return
type is some kind of collection of `DynamicTest`s. The class `DynamicTest` provides several static methods to create
those. You basically have to provide test data and based on it a display name as well as some kind of `Executable`.
In my example you can see me using the `stream()`-method of said class.

![img/15_testFactory.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/15_testFactory.png?raw=true)

![img/16_testFactory_result.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/16_testFactory_result.png?raw=true)

### Test-Extensions
Here I will show you an `Extension` that is not based on the `ParameterResolver` but instead implements the
`TestExecutionCondition`. The same thing that powers the `@Disabled` annotation. If we want to customize it we need out
own implementation. There are about a dozen of those `Extension` categories. `TestExecutionCondition` is just one of
them. Some are functional interfaces like the one we're talking about, others like the `ParameterResolver` are not.  
My example called `@DisabledOnMonday` does just that. It disables that test-method or -class on mondays. The 
implementation only checks for the weekday and returns an appropriate `ConditionEvaluationResult` resulting in the test
being ignored when the weekday matches.

![img/17_disabledConditions.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/17_disabledConditions.png?raw=true)

![img/18_disabledConditions_usage.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/18_disabledConditions_usage.png?raw=true)

Again this could without problem be placed on class level.


Advanced Test-Samples [(code)](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/src/test/java/com/drandarov/junit5/JUnit5_00_GeneralChanges.java)
------------------------------

### Extended disabled weekdays
Let's extend that `@DisabledOnMonday` annotation a bit. What if you want to choose the weekday? Creating 7 annotations
is kind of overkill. A way to achieve this could be to add another annotation that contains the weekdays:

![img/19_disabledOnWeekday.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/19_disabledOnWeekday.png?raw=true)

The `@DisabledWeekdays` annotation doesn't do much more than hold an int array corresponding to the weekdays.

![img/20_disabledOnWeekday_data.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/20_disabledOnWeekday_data.png?raw=true)

The extension looks slightly different now, since it needs to determine the weekdays from the annotation. Luckily the
`evaluate()`-method provides the `TestExtensionContext` so it's fairly easy to get those.

![img/21_disabledOnWeekday_annotation.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/21_disabledOnWeekday_annotation.png?raw=true)

### Extend Test-Annotation
So what if you want to save some that space occupied by all those annotations. Let's make it all-in-one in this example:

![img/22_uiTest.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/22_uiTest.png?raw=true)

What you basically do here is to create a new annotation and annotate that with `@Test`. Then you pack all you need in
there like your extensions, parameter resolvers, targets, parameters, etc. The annotation `@UITest` above looks like
this:

![img/23_uiTest_code.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/23_uiTest_code.png?raw=true)

The extensions used do not really matter here. One extension resolves the `Pane` from the fxml path and the other one 
just prints some data. This is rather a showcase of an `@Test`-Extension and including utilizing the extension features
of JUnit 5. If you want to see code nevertheless look into the repository.

### Benchmarking Example

As for the last example right now I will showcase some benchmarking possibilities and it isn't even that complicated.
There are several extensions that can be used for that. `BeforeAllCallback`, `BeforeTestExecutionCallback` and their
`After...`-aequivalents. Each of these interfaces has a method that will be executed at some point during the tests.
E.g. before each test or after etc. So by implementing those 4 interfaces in one extension we can create a class that
timestamps each time a method is called and after it finished including calculating the difference. Then we just need to
annotate an annotation `@Benchmarked` with that extension and then place that on top of a test-method or -class. Done.
The final benchmarked test-method will look something like this:

![img/24_benchmarked.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/24_benchmarked.png?raw=true)

The corresponding test-output:

![img/25_benchmarked_output.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/25_benchmarked_output.png?raw=true)

The extension couldn't be simpler:

![img/26_benchmarked_extension.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/26_benchmarked_extension.png?raw=true)

Of course I could have also included `@Benchmarked` in a separate `@BenchmarkedTest` annotation that would have extended
`@Test` as well saving that one line.


Closing words
-------------
### Contribution
Feel free to express critique and contribute to the 
[repository](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced) :)

### Further Reference
[Official JUnit 5 User Guide](http://junit.org/junit5/docs/current/user-guide)  
[JUnit 5 GitHub](https://github.com/junit-team/junit5)  
[JUnit 5 Milestone plan](https://github.com/junit-team/junit5/milestones/)  
