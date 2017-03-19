JUnit 5 Quick Start Guide and Advanced
======================================

Table of contents
-----------------

- [JUnit 5 Quick Start Guide and Advanced](#)
    - [Introduction](#introduction)
        - [Information](#information)
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
    - [Milestone 3 Changes](#milestone-3-changes)
        - [DiscoverySelectors](#discoveryselectors)
    - [Closing words](#closing-words)
        - [Contribution](#contribution)
        - [Usage](#usage)
        - [Schedule](#schedule)
        - [Further Reference](#further-reference)


Introduction
------------

### Information
Testing is important. We should all know that. They can help reproduce and fix problems in our code. They can also
remind you that you shouldn't have done something long after you forgot that you shouldn't do that thing and a lot more.

The problem is often that developers see testing as some overhead for developing that should be done after the code is
finished. That's wrong: Testing should be a vital part of development!  
That problem is something JUnit 5 can help you with. It introduced a lot of interesting, fast ways to create tests. Be
it Test-Factories, Test-Extensions (that only have to be implemented once), Lambda-Support etc. It pushed the D.R.Y.
principle a lot!

To understand this guide you should know the basics of testing and JUnit (4), but otherwise you wouldn't be here I guess.
You can also check out the best-practice-part included in this repository. It was created by request of a colleague:  
[**Checkout JUnit best-practices included in this repository**
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/src/test/java/com/drandarov/bestPractice/JUnitX_XX_BestPractice.java)

Also JUnit 5 supports running parallel to JUnit 4 if that's something you need.

Some headers related to code will have a code-link behind their name directing to the corresponding class in the
GitHub-Repository.  
For the whole source code see my GitHub-Repository:  
[**dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced**
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced)

### Set-Up
For an IDE I recommend *IntelliJ IDEA 2016.2(+)* right now since it brings native support for JUnit 5.  
As for dependencies:

*Maven*

```xml
  <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.0.0-M3</version>
        <scope>compile</scope>
  </dependency>
```
```xml
  <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-runner</artifactId>
        <version>1.0.0-M3</version>
        <scope>compile</scope>
  </dependency>
```
```xml
  <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.0.0-M3</version>
        <scope>runtime</scope>
  </dependency>
```

*Gradle*

```gradle
  testCompile group: 'org.junit.jupiter', name: 'junit-jupiter-api', version: '5.0.0-M3'
```
```gradle
  testCompile group: 'org.junit.platform', name: 'junit-platform-runner', version: '1.0.0-M3'
```
```gradle
  testRuntime group: 'org.junit.jupiter', name: 'junit-jupiter-engine', version: '5.0.0-M3'
```


General changes [(code)](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/src/test/java/com/drandarov/junit5/JUnit5_00_GeneralChanges.java)
------------------------

### Syntax
This paragraph contains the small or general changes made in the transition from JUnit 4 to JUnit 5. Those are simple
but still note worthy.

The first change is made to the most basic of things: the test and the `@Test`-annotation themselves. You no longer 
need to make the test `public`, however you can still not make it `static` or `private`. Also timeout and expected 
parameter functionality has moved elsewhere.

```java
/**
 * Tests are now only not allowed to be static / private. Latter also goes for @Before.../@After...
 * timeout = ? and expected = ? functionality has now moved elsewhere. See in 
 * {@link JUnit5_01_NewFeaturesBasics}
 */
@Test
void testTest() {}
```

### Naming

Other annotations have received slight changes as well, including the common `@BeforeClass`, `@BeforeEach`, their
`@After...` equivalents, `@Ignored` and the lesser known `@Category`. All of these have been renamed and given the
same treatment regarding `public` as `@Test`.

```java
/**
 * Annotation @BeforeClass was replaced by @{@link BeforeAll}. Needs to be static.
 * Same for @AfterClass.
 */
@BeforeAll
static void beforeAll() {}

/**
 * Annotation @Before was replaced by @{@link BeforeEach}.
 * Same for @After.
 */
@BeforeEach
void beforeEach() {}

/**
 * Annotation @Ignore was replaced by @{@link Disabled}. Sounds less negative.
 * However a reason for the deactivation  will be printed.
 */
@Disabled
@Test
void disabledTest() {}

/**
 * JUnit 4's experimental @Category is now called {@link Tag}/{@link Tags}.
 */
@Tag("abc")
@Test
void taggedTest() {}
```

`Assert` and `Assume` classes have been renamed as well and are now called `Assertions` and `Assumptions`. Not much has
changed for the naming of the methods of both classes.

```java
/**
 * Assertion Methods are now in class {@link Assertions}. Method names stayed mostly the same 
 * otherwise.
 */
@Test
void assertionsTest() {
    Assertions.assertTrue(true); // Without static import
    assertTrue(true);            // With static import on org.junit.jupiter.api.Assertions.assertTrue()
}

/**
 * Assumption Methods are now in class {@link Assumptions}. Method names stayed mostly the same 
 * otherwise.
 */
@Test
void assumptionsTest() {
    Assumptions.assumeTrue(true); // Without static import
    assumeTrue(true);             // With static import on org.junit.jupiter.api.Assumptions.assumeTrue()
}
```


New features: Basics [(code)](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/src/test/java/com/drandarov/junit5/JUnit5_01_NewFeaturesBasics.java)
-----------------------------

### General
Here I want to introduce some basics for the new features available in the new version.  
There is a new pretty annotation called `@DisplayName` which is supposed to improve the readability of test reports, so
you don't need 40-character test-names to make clear what the test is about at a glance.

```java
/**
 * Tests can now receive Display-Names via @{@link DisplayName}. These are e.g. used by the IDE,
 * Console or the {@link TestInfo}-Parameter (addressed in
 * {@link #parameterTest(TestInfo, TestReporter)}).
 */
@Test
@DisplayName("Choose a display name")
void displayNameTest() {}
```

![img/04_displayname_result.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/04_displayname_result.png?raw=true)

You can now also group tests with inner classes annotated with `@Nested`.

```java
@Nested
@DisplayName("Tests grouped by something")
class groupedTests {

    @Test
    void groupedTest1() {}

    @Test
    void groupedTest2() {}

}
```

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

```java
/**
 * The new assertion-methods now support supplier-interfaces, meaning you can now enter lambda
 * expressions on the fly to a lot of the assert-methods. E.g. by giving a {@link BooleanSupplier}
 * for the assertion and a ({@link Supplier<String>} for the result-message to the
 * {@link Assertions#assertTrue(BooleanSupplier, Supplier)} method.
 */
@Test
void assertLambdaTest() {
    assertTrue(() -> Boolean.parseBoolean("true")); // Simple assertTrue() with BooleanSupplier-Lambda-Implement.
    Assertions.assertTrue(true, this.getClass()::getName); // Method references are possible as well of course
}
```

A new important functional interface is `Executable`. It is very similar to a `Runnable`, however it throws a
`Throwable` meaning you can execute assertions like `assertTrue()` and an `AssertionError` may be thrown affecting your
test-result. It is used in several assertions like the new `assertAll(Executable... executables)` which can be also used
to prevent repetition.

```java
/**
 * {@link Assertions} has a method called {@link Assertions#assertAll(Executable...)} that enables
 * us to group assertions, as well as reuse them.
 */
@Test
void assertAllTest() {
    Executable[] executables = {
        () -> assertTrue(getData() >= -10),
        () -> assertTrue(getData() <= +15)};

    Assertions.assertAll("Random Tests", executables);
    dataChanges();
    Assertions.assertAll("Random Tests Again", executables);
}
```

This new functional interface is also used in the new replacement of the old `@Test`-parameter `expected` which is
called `assertThrows()`. It asserts whether an exception was thrown.
If you need the exception-instance itself to e.g. assert the message, you can instead use `expectThrows()` which also
has the exception as return type.

```java
/**
 * The expected parameter of {@link Test} has moved to
 * {@link Assertions#assertThrows(Class, Executable)}.
 */
@Test
void assertThrowsTest() {
    assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> (new String[1])[2] = "I will throw an Exception :)");
}
```

```java
/**
 * You can also use {@link Assertions#assertThrows(Class, Executable)} to get the
 * {@link Exception}-Instance if you need it.
 */
@Test
void expectThrowsTest() {
    ArrayIndexOutOfBoundsException exc = assertThrows(ArrayIndexOutOfBoundsException.class,
            () -> (new String[1])[2] = "I will throw an Exception :)");

    assertEquals(exc.getMessage(), "2");
}
```

### Parameter Resolver

The biggest new feature in JUnit 5 is the new Extension-API. A part of it is the `ParameterResolver`-Interface which is
an extension of the `Extension`-Interface itself. The `ParameterResolver`-Interface provide a way for dependency
injection on method level by injecting data into test-method parameters.  
JUnit 5 provides two implementations by itself: `TestInfo` which contains some meta information and the appropriate
Test-`Method` and Test-`Class` instances and `TestReporter` which can be used to publish test entries.  
A lot more on the Extension-Api is following further below.

```java
/**
 * Tests can now be provided with parameters. Those are resolved by
 * {@link ParameterResolver}-Implementations which in turn are extensions of the above mentioned
 * {@link Extension}. This enables dependency injection at method level.
 *
 * Resolvers for {@link TestInfo} and {@link TestReporter} are already provided. Other parameters
 * require your own {@link ParameterResolver}-Implementations to be added with the
 * @{@link ExtendWith}-Annotation to either the class or method.
 *
 * @param testInfo Information about the current test
 * @param testReporter Used to publish test entries
 */
@Test
void parameterTest(TestInfo testInfo, TestReporter testReporter) {
    System.out.println("DisplayName:\t" + testInfo.getDisplayName());
    System.out.println("Tags:\t\t\t" + testInfo.getTags());
    System.out.println("TestClass:\t\t" + testInfo.getTestClass());
    System.out.println("TestMethod:\t\t" + testInfo.getTestMethod());

    testReporter.publishEntry("parameterTestTime", Long.toString(System.currentTimeMillis()));
}
```


New features: Advanced [(code)](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/src/test/java/com/drandarov/junit5/JUnit5_02_NewFeaturesAdvanced.java)
-------------------------------

### Test-Parameters
Building upon the `ParameterResolver` paragraph of the last chapter let's look at implementing your own
`ParameterResolver`. You can also see the first visual sign of the Extension-API in the form of the
`@ExtendWith`-Annotation. The final result is:

```java
/**
 * A simple example of a {@link ParameterResolver}-Implementation. @{@link ExtendWith} is used to
 * mark {@link ClassName_ParameterResolver} and {@link ParameterIndex_ParameterResolver} as used
 * {@link ParameterResolver}. These could alternatively be placed at class level.
 *
 * @param className String-Parameter that will be injected by {@link ClassName_ParameterResolver}
 * @param parameterIndex Long-Parameter that will be injected by {@link ParameterIndex_ParameterResolver}
 */
@Test
@ExtendWith({ClassName_ParameterResolver.class, ParameterIndex_ParameterResolver.class})
void customParameterTest(String className, Long parameterIndex) {
    System.out.println(className);       // Surrounding class name injected by ClassName_ParameterResolver
    System.out.println(parameterIndex);  // Parameter-Index injected by ParameterIndex_ParameterResolver
}
```

This is achieved by the following implementations:

The first implementation processes the `String` parameter `className`. It checks whether the parameter class is a
`String` and throws an exception otherwise. To resolve and inject the parameter it just returns the test classes name.

```java
public class ClassName_ParameterResolver implements ParameterResolver {

    /**
     * Simple example that only checks if the Parameter-Type is a {@link String} based on the
     * Parameter-Context to determine whether the Parameter is supported by this
     * {@link ParameterResolver}.
     */
    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) 
                                                                throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(String.class);
    }

    /**
     * Simple example that simply resolves the Parameter by returning the Class-Name based on
     * the Parameter-Context.
     */
    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) 
                                                                throws ParameterResolutionException {
        Class<?> contextClass = extensionContext.getTestClass().orElse(null);
        return contextClass == null ? null : contextClass.getSimpleName();
    }

}
```

The seconds implementation processes the `Long` parameter `parameterIndex`. It does basically the same but resolves the
parameter by getting the index from the `parameterContext`.

```java
public class ParameterIndex_ParameterResolver implements ParameterResolver {

    /**
     * Simple example that only checks if the Parameter-Type is a {@link Long} based on the
     * Parameter-Context to determine whether the Parameter is supported by this
     * {@link ParameterResolver}.
     */
    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext)
                                                                throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(Long.class);
    }

    /**
     * Simple example that simply resolves the Parameter by returning the parameterIndex based
     * on the Parameter-Context.
     */
    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext)
                                                                throws ParameterResolutionException {
        return (long) parameterContext.getIndex();
    }

}
```

### Test-Factories
Another big feature are the new Test-Factories. These are annotated with `@TestFactory` instead of `@Test`. Their return
type is some kind of collection of `DynamicTest`s. The class `DynamicTest` provides several static methods to create
those. You basically have to provide test data and based on it a display name as well as some kind of `Executable`.
In my example you can see me using the `stream()`-method of said class.

```java
/**
 * An example for a {@link TestFactory} with JUnit 5.
 * {@link DynamicTest#stream(Iterator, Function, ThrowingConsumer)} provides an easy way to
 * factorize multiple tests, which will be executed automatically.
 * It's basically similar to a for-loop that reads data and asserts, but these test will be
 * grouped and displayed separately in the test results.
 *
 * @return A stream of dynamic tests
 */
@TestFactory
Stream<DynamicTest> testStreamFactoryTest() {
    Iterator<String> testData = Arrays.asList(new String[]{"1", "2", "3"}).iterator();

    return DynamicTest.stream(
            testData,                              // Input-Data for the Factory
            s -> "Displayname: S" + s,             // Creating DisplayNames for the test
            Assertions::assertNotNull);            // Providing an Executable on which the test is based
}
```

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

```java
/**
 * An extension that disables a test class on Mondays, because nobody likes those, right?
 *
 * @author dmitrij-drandarov
 * @since 28 Jul 2016
 */
public class DisabledOnMonday implements TestExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluate(TestExtensionContext context) {
        boolean monday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;

        return monday ?
                ConditionEvaluationResult.disabled("I spare you on Mondays.")
                :
                ConditionEvaluationResult.enabled("Don't spare you on other days though >:(");
    }

}
```

```java
/**
 * For this example I use my implementation of {@link TestExecutionCondition} called
 * {@link DisabledOnMonday} to tell JUnit to disable this test on mondays, because who likes
 * those, right?
 *
 * This annotation might just as well be placed on class level. To see how I implemented this look at
 * {@link DisabledOnMonday}.
 */
@Test
@ExtendWith(DisabledOnMonday.class)
void disabledOnMondayTest() {}
```

Again: This could without problem be placed on class-level.


Advanced Test-Samples [(code)](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/src/test/java/com/drandarov/junit5/JUnit5_00_GeneralChanges.java)
------------------------------

### Extended disabled weekdays
Let's extend that `@DisabledOnMonday` annotation a bit. What if you want to choose the weekday? Creating 7 annotations
is kind of overkill. A way to achieve this could be to add another annotation that contains the weekdays:

```java
/**
 * Here I go a step further and annotate my days dynamically, by specifying the days I don't want
 * the test to run on with another custom annotation called @{@link DisabledWeekdays}.
 *
 * My extension {@link DisabledOnWeekday} later searches for @{@link DisabledWeekdays} and determines
 * whether the test should run or not.
 */
@Test
@DisabledWeekdays({Calendar.THURSDAY, Calendar.SATURDAY})
@ExtendWith(DisabledOnWeekday.class)
void disabledOnWeekdaysTest() {}
```

The `@DisabledWeekdays` annotation doesn't do much more than hold an int array corresponding to the weekdays.

```java
/**
 * A simple annotation to retain information about weekdays that the annotated tests are disabled on.
 * Used by {@link DisabledOnWeekday}-Extension.
 *
 * @author dmitrij-drandarov
 * @since 28 Jul 2016
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DisabledWeekdays {
    int[] value();
}
```

The extension looks slightly different now, since it needs to determine the weekdays from the annotation. Luckily the
`evaluate()`-method provides the `TestExtensionContext` so it's fairly easy to get those.

```java
/**
 * An extension that disables this test class on the weekday specified by {@link DisabledWeekdays}.
 *
 * @author dmitrij-drandarov
 * @since 28 Jul 2016
 */
public class DisabledOnWeekday implements TestExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluate(TestExtensionContext context) {

        // Search for the @DisabledWeekdays annotation from the TestExtensionContext
        Optional<AnnotatedElement> contextElement = context.getElement();
        AnnotatedElement annotatedElement = contextElement.orElse(null);

        if (annotatedElement != null) {
            DisabledWeekdays weekdayAnnotation = annotatedElement.getAnnotation(DisabledWeekdays.class);

            // Determine whether the test should be disabled
            boolean weekdayToday = IntStream.of(weekdayAnnotation.value())
                    .anyMatch(day -> day == Calendar.getInstance().get(Calendar.DAY_OF_WEEK));

            // Return a ConditionEvaluationResult based on the outcome of the boolean weekdayToday
            return weekdayToday ?
                    ConditionEvaluationResult.disabled("I spare you today.")
                    :
                    ConditionEvaluationResult.enabled("Don't spare you on other days though >:(");
        }
        return null;
    }

}
```

### Extend Test-Annotation
So what if you want to save some that space occupied by all those annotations. Let's make it all-in-one for this example:

```java
/**
 * Here I use an annotation @{@link UITest} that is annotated by @{@link Test} itself, so it will be
 * executed properly. @{@link UITest} contains grouped information and annotations about this test
 * like predefined extensions. Further information in @{@link UITest}s JavaDoc.
 *
 * This of course could be also possible for the examples above.
 */
@UITest("../../sample.fxml")
void userInterfaceTest(Pane root) {
    System.out.println(root.getPrefWidth());    //555.0 (defined in FXML-File)
    System.out.println(root.getPrefHeight());   //333.0 (defined in FXML-File)
}
```

What you basically do here is to create a new annotation and annotate that with `@Test`. Then you pack all you need in
there like your extensions, parameter resolvers, targets, parameters, etc. The annotation `@UITest` above looks like
this:

```java
/**
 * Test annotated by this will be executed by the test runner without problems due to @{@link Test}
 * being included.
 * You can basically group annotations by doing this and save some space, by not having to add all
 * those {@link ExtendWith}s etc. to each method.
 * Readability inside the test classes is the key here. And it looks cooler ;)
 *
 * @author dmitrij-drandarov
 * @since 29 Jul 2016
 */
@Test
@Tag("userInterface")                      // For simple identification by ParameterResolvers
@ExtendWith(PrintUITestData.class)         // Prints UI Test Data before each test
@ExtendWith(RootElementResolver.class)     // Resolves the root pane
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)        // Required for the test to be automatically executed
public @interface UITest {

    /**
     * FXML-Path.
     *
     * @return FXML-Path used for the UI-Test.
     */
    String value();

}
```

The extensions used do not really matter here. One extension resolves the `Pane` from the fxml path and the other one 
just prints some data. This is rather a showcase of an `@Test`-Extension and including utilizing the extension features
of JUnit 5. If you want to see code nevertheless look into the repository.

### Benchmarking Example

As for the last example right now I will showcase some benchmarking possibilities and it isn't even that complicated.
There are several extensions that can be used for that. `BeforeAllCallback`, `BeforeTestExecutionCallback` and their
`After...`-equivalents. Each of these interfaces has a method that will be executed at some point during the tests.
E.g. before each test or after etc. So by implementing those 4 interfaces in one extension we can create a class that
timestamps each time a method is called and after it finished including calculating the difference. Just as a
disclaimer: Don't use `System.out` to print during tests in general. This is just for the sake of an examble! Don't do
this at home/in your projects kids. Then we just need to annotate an annotation `@Benchmarked` with that extension and
then place that on top of a test-method or -class. Done! The final benchmarked test-method will look something like
this:

```java
/**
 * For this example I wrote an annotation @{@link Benchmarked} that doesn't include @{@link Test} -
 * which it could - but instead only contains an self-written extension called
 * {@link BenchmarkExtension}. Annotating your class with this will basically provide you with
 * automatic benchmarking.
 *
 * This could of course be also placed on top of the class.
 */
@Test
@Benchmarked
void benchmarkedTest() {
    List<Integer> primes = new ArrayList<>();
    System.out.println("Calculating some primes...");
    IntStream.iterate(2, i -> i + 1)
            .filter(i -> LongStream.rangeClosed(2, (long)(Math.sqrt(i))).allMatch(n -> i % n != 0))
            .limit(55555)
            .forEach(primes::add);
}
```

The corresponding test-output:

![img/25_benchmarked_output.png
](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/img/25_benchmarked_output.png?raw=true)

The extension couldn't be simpler:

```java
/**
 * Extension, that does the logging for the benchmarks.
 *
 * @author dmitrij-drandarov
 * @since 29 Jul 2016
 */
public class BenchmarkExtension implements BeforeAllCallback, BeforeTestExecutionCallback,
        AfterTestExecutionCallback, AfterAllCallback {

    private static final String APD = "\t-\t";

    private static final Map<String, Long> startTime = new HashMap<>();
    private static final DateFormat dtForm = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);


    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long start = currentTimeMillis();

        System.out.println("#### Summary           \t" + APD + disp + " ####");
        System.out.println("#### Start of Benchmark\t" + APD + disp + APD + dtForm.format(new Date(start)) + " ####");
        startTime.put(disp, start);
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long start = currentTimeMillis();

        System.out.println("#### Method-Benchm. ####" + APD + disp + APD + dtForm.format(new Date(start)));
        startTime.put(context.getDisplayName(), start);
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long end = currentTimeMillis();

        System.out.println("#### Summary        ####" + APD + disp);
        System.out.println("#### Start          ####" + APD + dtForm.format(new Date(startTime.get(disp))));
        System.out.println("#### End            ####" + APD + dtForm.format(new Date(end)));
        System.out.println("#### Duration       ####" + APD + (end - startTime.get(disp)) + " ms\n");
    }

    @Override
    public void afterAll(ContainerExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long end = currentTimeMillis();

        System.out.println("#### End of Benchmark  \t" + APD + disp + APD + dtForm.format(new Date(end)) + " ####");
        System.out.println("#### Duration for class\t" + APD + disp + APD + (end - startTime.get(disp)) + " ms ####");
    }

}
```

Of course I could have also included `@Benchmarked` in a separate `@BenchmarkedTest` annotation that would have extended
`@Test` as well saving that one line.

Milestone 3 changes [(code)](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced/blob/master/src/test/java/com/drandarov/junit5/JUnit5_04_M3_DiscoverySelectors.java)
----------------------------

### DiscoverySelectors
TODO :c

Closing words
-------------

### Contribution
Feel free to express critique and contribute to the 
[repository](https://github.com/dmitrij-drandarov/JUnit5-Quick-Start-Guide-and-Advanced) :)

### Usage
You can use this repository in any way you want. May it be for workshops or presentations. Just give credits. ;)

### Schedule
- [ ] 5.0 M3 Update - TODO: Update guide
 - JUnit 4 interoperability
 - Additional discovery selectors

- [ ] 5.0 M4 Update - Due by April 1 18, 2017
 - Parameterized tests
 - Enhanced dynamic tests
 - Documentation
 
- [ ] 5.0 M5 Update - Due by June 25, 2017
 - Scenario tests
 - Repeated tests
 - Test execution in user-defined thread

- [ ] 5.0 RC1 (Release Candidate 1) Update - Due by July 23, 2017
 - Last fixes before GA

- [ ] 5.0 GA (General Availability Release) Update - Due by August 24, 2017
 
### Further Reference
[Official JUnit 5 User Guide](http://junit.org/junit5/docs/current/user-guide)  
[JUnit 5 GitHub](https://github.com/junit-team/junit5)  
[JUnit 5 Milestone plan](https://github.com/junit-team/junit5/milestones/)  
