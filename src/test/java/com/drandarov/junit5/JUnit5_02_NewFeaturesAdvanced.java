package com.drandarov.junit5;

import com.drandarov.junit5.utilizations.parameterresolver.ClassName_ParameterResolver;
import com.drandarov.junit5.utilizations.simpleextension.*;
import com.drandarov.junit5.utilizations.parameterresolver.ParameterIndex_ParameterResolver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A collection of new, advanced or experimental features introduced in JUnit 5.
 *
 * Created by dmitrij-drandarov on 22.07.2016.
 */
public class JUnit5_02_NewFeaturesAdvanced {

    /*
    ##################################################################################################################
                                                    Test-Parameters
    ##################################################################################################################
    */

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
        System.out.println(className);       // Surrounding class name injected by ClassName_ParameterResolver
        System.out.println(parameterIndex);  // Parameter-Index injected by ParameterIndex_ParameterResolver
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


    /*
    ##################################################################################################################
                                                    Test-Extensions
    ##################################################################################################################
    */

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
     * In this example I use my implementation of {@link TestExecutionCondition} called {@link DisabledOnMonday} to
     * tell JUnit to disable this test on mondays, because who likes those, right?
     *
     * This annotation might just as well be placed on class level. To see how I implemented this look at
     * {@link DisabledOnMonday}.
     */
    @Test
    @ExtendWith(DisabledOnMonday.class)
    void disabledOnMondayTest() {}

}
