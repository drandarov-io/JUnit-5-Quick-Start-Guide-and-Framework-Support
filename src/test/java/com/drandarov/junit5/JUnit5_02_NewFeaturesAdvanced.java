package com.drandarov.junit5;

import com.drandarov.junit5.utils.parameterresolver.ClassName_ParameterResolver;
import com.drandarov.junit5.utils.parameterresolver.ParameterIndex_ParameterResolver;
import com.drandarov.junit5.utils.simpleextension.DisabledOnMonday;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.function.ThrowingConsumer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * A collection of new, advanced or experimental features introduced in JUnit 5.
 *
 * @author dmitrij-drandarov
 * @since 22 Jul 2016
 */
class JUnit5_02_NewFeaturesAdvanced {

    private static final Logger LOG = Logger.getGlobal();

    /*##############################################
    #           Test-Parameters
    ##############################################*/

    /**
     * A simple example of a {@link ParameterResolver}-Implementation. @{@link ExtendWith} is used to mark
     * {@link ClassName_ParameterResolver} and {@link ParameterIndex_ParameterResolver} as used
     * {@link ParameterResolver}. These could alternatively be placed at class level.
     *
     * @param className String-Parameter that will be injected by {@link ClassName_ParameterResolver}
     * @param parameterIndex Long-Parameter that will be injected by {@link ParameterIndex_ParameterResolver}
     */
    @Test
    @ExtendWith({ClassName_ParameterResolver.class, ParameterIndex_ParameterResolver.class})
    void customParameterTest(String className, Long parameterIndex) {
        LOG.info(className);                    // Surrounding class name injected by ClassName_ParameterResolver
        LOG.info(parameterIndex.toString());    // Parameter-Index injected by ParameterIndex_ParameterResolver
    }


    /*##############################################
    #           Test-Factories
    ##############################################*/

    /**
     * An example for a {@link TestFactory} with JUnit 5.
     * {@link DynamicTest#stream(Iterator, Function, ThrowingConsumer)} provides an easy way to factorize multiple
     * tests, which will be executed automatically.
     * It's basically similar to a for-loop that reads data and asserts, but these test will be grouped and displayed
     * separately in the test results.
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


    /*##############################################
    #           Test-Extensions
    ##############################################*/

    /**
     * All possible extensions are implementations of {@link Extension} or its
     * extensions since those are required for {@link ExtendWith} or {@link Extensions}.
     *
     * The default implementations are currently inside {@link org.junit.jupiter.api.extension} and a list of them
     * inside the JavaDoc of {@link ExtendWith}.
     */
    @Test
    void extensionInfoTest() {}

    /**
     * For this example I use my implementation of {@link ExecutionCondition} called {@link DisabledOnMonday} to
     * tell JUnit to disable this test on mondays, because who likes those, right?
     *
     * This annotation might just as well be placed on class level. To see how I implemented this look at
     * {@link DisabledOnMonday}.
     */
    @Test
    @ExtendWith(DisabledOnMonday.class)
    void disabledOnMondayTest() {}

}
