package com.drandarov.junit5;

import com.drandarov.junit5.util.LongParameterResolver;
import com.drandarov.junit5.util.StringParameterResolver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A collection of new, advanced features introduced in JUnit 5.
 *
 * Created by drandard on 22.07.2016.
 */
public class JUnit5_02_NewAdvancedFeatures {

    /*
    ##################################################################################################################
                                                    Test-Extensions
    ##################################################################################################################
    */


    /*
    ##################################################################################################################
                                                    Test-Parameters
    ##################################################################################################################
    */

    /**
     * Tests can now be provided with parameters. Those are resolved by {@link ParameterResolver}-Implementations.
     * This enables dependency injection at method level.
     *
     * Resolvers for {@link TestInfo} and {@link TestReporter} are already provided. Other parameters require your own
     * {@link ParameterResolver}-Implementations to be added with the @{@link ExtendWith}-Annotation.
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
     * A simple example of a custom parameter. @{@link ExtendWith} is used to mark {@link StringParameterResolver} as
     * used {@link ParameterResolver}
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
        Iterator<String> iterator = Arrays.asList(new String[]{"1", "2", "3"}).iterator();

        Stream<DynamicTest> dynamicTests = DynamicTest.stream(
                iterator,                              // Input-Data for the Factory
                s -> "Displayname: S" + s,             // Creating DisplayNames for the test
                Assertions::assertNotNull);            // Providing an Executable on which the test is based

        return dynamicTests;
    }

    /**
     * Another {@link TestFactory}, but this time returns a List with {@link DynamicTest}s.
     *
     * @return A list of dynamic tests
     */
    @TestFactory
    List<DynamicTest> testListFactoryTest() {
        List<DynamicTest> dynamicTests = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            dynamicTests.add(DynamicTest.dynamicTest(           //Factory-Method for DynamicTests
                    "DisplayName: L" + i,                       //DisplayName here
                    () -> assertTrue(this.getClass() != null)   //Executable here
            ));

        return dynamicTests;
    }

}
