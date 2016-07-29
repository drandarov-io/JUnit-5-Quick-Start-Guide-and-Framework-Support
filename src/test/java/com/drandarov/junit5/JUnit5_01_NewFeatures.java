package com.drandarov.junit5;

import org.junit.jupiter.api.*;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Contains a collection of smaller, general new features like lambda support or small annotations.
 *
 * Created by dmitrij-drandarov on 22.07.2016.
 */
public class JUnit5_01_NewFeatures {

    /*
    ##################################################################################################################
                                                      Test-Methods
    ##################################################################################################################
    */

    /**
     * Tests can now receive Display-Names via @{@link DisplayName}. These are e.g. used by the IDE, Console or with
     * Test-Paramets (adressed in {@link JUnit5_02_NewAdvancedFeatures}).
     */
    @Test
    @DisplayName("Parameter-Test")
    void displayNameTest() {}


    /*
    ##################################################################################################################
                                             Assertions / Assumptions
    ##################################################################################################################
    */

    /**
     * JUnit 5 includes several new Functional-Interface. The most important one is called {@link Executable}.
     * It is used for certain assertions such as {@link Assertions#assertAll(Executable...)} and
     * {@link Assertions#assertThrows(Class, Executable)}
     */
    @Test
    void executablesTest() {
        Executable ex = () -> assertTrue(true);
        assertAll(ex);
    }

    /**
     * The new assertion-methods now support supplier-interfaces, meaning you can now enter lambda expressions on the
     * fly to all the assert-methods.
     * This does also include suppliers for test-messages.
     */
    @Test
    void assertLambdaTest() {
        assertTrue(() -> Boolean.parseBoolean("true")); // Simple assertTrue() with BooleanSupplier-Lambda-Implement.
        Assertions.assertTrue(true, this.getClass()::getName); // Method references are possible as well of course
    }

    /**
     * The expected parameter of {@link Test} has moved to {@link Assertions#assertThrows(Class, Executable)}.
     */
    @Test
    void assertThrowsTest() {
        assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> (new String[1])[2] = "I will throw an Exception :)");
    }

    /**
     * {@link Assertions} has a method called {@link Assertions#assertAll(Executable...)} that enables us to group
     * assertions, as well as reuse them.
     */
    @Test
    void assertAllTest() {
        BigInteger i = BigInteger.valueOf(10L);

        Executable[] executables = {
            () -> assertTrue(true),
            () -> assertFalse(10 <= 5),
            () -> assertTrue(i.intValue() <= 15)};

        Assertions.assertAll("Random Tests", executables);

        i.add(BigInteger.ONE); //TODO: What did I think here... @me: Think of something better

        Assertions.assertAll("Random Tests Again", executables);
    }

    /**
     * {@link Assumptions} now support Suppliers as well.
     */
    @Test
    void assumptionsLambdaTest() {
        Assumptions.assumeTrue(() -> Boolean.parseBoolean("true"));
    }

}
