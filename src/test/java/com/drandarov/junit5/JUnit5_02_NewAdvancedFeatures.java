package com.drandarov.junit5;

import com.drandarov.junit5.util.extension.BeforeAllExtensionImpl;
import com.drandarov.junit5.util.extension.DisabledOnMonday;
import com.drandarov.junit5.util.extension.DisabledOnWeekday;
import com.drandarov.junit5.util.extension.DisabledWeekdays;
import com.drandarov.junit5.util.extension.parameterresolver.LongParameterResolver;
import com.drandarov.junit5.util.extension.parameterresolver.StringParameterResolver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

    /*
    ##################################################################################################################
                                                    Test-Extensions
    ##################################################################################################################
    */

/**
 * A collection of new, advanced or experimental features introduced in JUnit 5.
 *
 * Created by dmitrij-drandarov on 22.07.2016.
 */
@ExtendWith(BeforeAllExtensionImpl.class) // This example is basically an alternative to @BeforeAll
public class JUnit5_02_NewAdvancedFeatures {

    /**
     * All possible extensions are implementations of {@link Extension} or its
     * extensions since those are requiered for {@link ExtendWith} or {@link Extensions}.
     *
     * The default implementations are currently inside {@link org.junit.jupiter.api.extension} and a list of them
     * inside the JavaDoc of {@link ExtendWith}.
     */
    @Test
    void extensionInfoTest() {}

    /**
     * In this example I use an implementation of {@link TestExecutionCondition} called {@link DisabledOnMonday} to
     * tell JUnit to disable this test on mondays, because who likes those, right?
     *
     * This annotation might just as well be placed on class level. To see how I implemented this look at
     * {@link DisabledOnMonday}.
     */
    @Test
    @ExtendWith(DisabledOnMonday.class)
    void disabledOnMondayTest() {}

    /**
     * Here I go a step further and annotate my days dynamically, by specifying the days I don't want the test to run
     * on with another custom annotation called {@link DisabledWeekdays}.
     *
     * My extension {@link DisabledOnWeekday} later searches for that annoation and determines whether the test should
     * run or not.
     */
    @Test
    @DisabledWeekdays({Calendar.THURSDAY, Calendar.SATURDAY})
    @ExtendWith(DisabledOnWeekday.class)
    void disabledOnWeekdaysTest() {}


    /*
    ##################################################################################################################
                                                    Test-Parameters
    ##################################################################################################################
    */

    /**
     * Tests can now be provided with parameters. Those are resolved by {@link ParameterResolver}-Implementations which
     * in turn are extensions of the above mentioned {@link Extension}.
     * This enables dependency injection at method level.
     *
     * Resolvers for {@link TestInfo} and {@link TestReporter} are already provided. Other parameters require your own
     * {@link ParameterResolver}-Implementations to be added with the @{@link ExtendWith}-Annotation to either the
     * class or method.
     *
     * @param testInfo Information about the current test
     * @param testReporter TODO: ?!
     */
    @Test
    void parameterTest(TestInfo testInfo, TestReporter testReporter) {
        System.out.println("DisplayName:\t" + testInfo.getDisplayName());
        System.out.println("Tags:\t\t\t" + testInfo.getTags());
        System.out.println("TestClass:\t\t" + testInfo.getTestClass());
        System.out.println("TestMethod:\t\t" + testInfo.getTestMethod());

        //TODO: What is this?
        testReporter.publishEntry("parameterTestTime", Long.toString(System.currentTimeMillis()));
    }

    /**
     * A simple example of a custom parameter. @{@link ExtendWith} is used to mark {@link StringParameterResolver} and
     * {@link LongParameterResolver} as used {@link ParameterResolver}.
     * These could alternatively be placed at class level.
     *
     * @param parameterString String-Parameter that will be injected by {@link StringParameterResolver}
     * @param parameterLong Long-Parameter that will be injected by {@link LongParameterResolver}
     */
    @Test
    @ExtendWith({StringParameterResolver.class, LongParameterResolver.class})
    void customParameterTest(String parameterString, Long parameterLong) {
        System.out.println(parameterString);
        System.out.println(parameterLong);
    }


    /*
    ##################################################################################################################
                                                     Test-Factories
    ##################################################################################################################
    */

    /**
     * An example for a {@link TestFactory} with JUnit 5. {@link DynamicTest#stream(Iterator, Function, Consumer)}
     * provides an easy way to factorize multiple tests, which will be executed automatically.
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

    /**
     * Another {@link TestFactory}, but this time returns a List with {@link DynamicTest}s.
     *
     * @return A list of dynamic tests
     */
    @TestFactory
    List<DynamicTest> testListFactoryTest() {
        // For each number in the range a DynamicTest will be created and collected into a list to be later executed.
        return IntStream.rangeClosed(1, 3) // Input-Data
                .mapToObj(i -> DynamicTest.dynamicTest("DisplayName: L" + i, () -> assertTrue(i > 0)))
                .collect(Collectors.toList());
    }

}
