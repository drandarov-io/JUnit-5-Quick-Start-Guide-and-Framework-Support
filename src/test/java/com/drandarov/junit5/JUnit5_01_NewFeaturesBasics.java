package com.drandarov.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.function.Executable;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Contains a collection of smaller, general new features like lambda support or small annotations.
 *
 * @author dmitrij-drandarov
 * @since 22 Jul 2016
 */
class JUnit5_01_NewFeaturesBasics {

    /*
    ##################################################################################################################
                                                      Test-Methods
    ##################################################################################################################
    */

    /**
     * Tests can now receive Display-Names via @{@link DisplayName}. These are e.g. used by the IDE, Console or the
     * {@link TestInfo}-Parameter (addressed in {@link #parameterTest(TestInfo, TestReporter)}).
     */
    @Test
    @DisplayName("Choose a display name")
    void displayNameTest() {}

    @Nested
    @DisplayName("Tests grouped by something")
    class groupedTests {

        @Test
        void groupedTest1() {}

        @Test
        void groupedTest2() {}

    }

    /*
    ##################################################################################################################
                                             Assertions / Assumptions
    ##################################################################################################################
    */

    /**
     * JUnit 5 includes several new Functional-Interface. One of the most important one is called {@link Executable}.
     * It is used for certain assertions such as {@link Assertions#assertAll(Executable...)} and
     * {@link Assertions#assertThrows(Class, Executable)}
     */
    @Test
    void executablesTest() {
        Executable ex = () -> assertTrue(() -> 5 <= 10);
        Executable ex2 = () -> assertFalse(Boolean.FALSE);
        assertAll(ex, ex2);
    }

    /**
     * The new assertion-methods now support supplier-interfaces, meaning you can now enter lambda expressions on the
     * fly to a lot of the assert-methods.
     * E.g. by giving a {@link BooleanSupplier} for the assertion and a ({@link Supplier<String>} for the
     * result-message to the {@link Assertions#assertTrue(BooleanSupplier, Supplier)} method.
     */
    @Test
    void assertLambdaTest() {
        assertTrue(() -> Boolean.parseBoolean("true")); // Simple assertTrue() with BooleanSupplier-Lambda-Implement.
        Assertions.assertTrue(true, this.getClass()::getName); // Method references are possible as well of course
    }

    /**
     * {@link Assertions} has a method called {@link Assertions#assertAll(Executable...)} that enables us to group
     * assertions, as well as reuse them.
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

    /**
     * The expected parameter of {@link Test} has moved to {@link Assertions#assertThrows(Class, Executable)}.
     */
    @Test
    void assertThrowsTest() {
        assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> (new String[1])[2] = "I will throw an Exception :)");
    }

    /**
     * You can also use {@link Assertions#assertThrows(Class, Executable)} to get the {@link Exception}-Instance if you
     * need it.
     */
    @Test
    void expectThrowsTest() {
        ArrayIndexOutOfBoundsException exc = assertThrows(ArrayIndexOutOfBoundsException.class,
                () -> (new String[1])[2] = "I will throw an Exception :)");

        assertEquals(exc.getMessage(), "2");
    }

    private int i = 10;
    private void dataChanges() { i++; }
    private int getData() { return i; }

    /**
     * {@link Assumptions} of course support Suppliers as well.
     */
    @Test
    void assumptionsLambdaTest() {
        Assumptions.assumeTrue(() -> Boolean.parseBoolean("true"));
    }

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

}
