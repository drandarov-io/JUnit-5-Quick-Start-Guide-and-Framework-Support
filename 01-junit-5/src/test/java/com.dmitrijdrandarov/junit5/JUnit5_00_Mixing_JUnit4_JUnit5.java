package com.dmitrijdrandarov.junit5;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;

/**
 * This all happens within the JUnit 5 environment! Therefore you can make the class package-private.
 *
 * JUnit 5 is managed by:
 * org.junit.jupiter:junit-jupiter-api:5.0.0
 *
 * JUnit 4 lagacy support is managed by:
 * org.junit.vintage:junit-vintage-engine:4.12.0
 *
 * Public access modifier is still required on mixed test classes.
 */
public class JUnit5_00_Mixing_JUnit4_JUnit5 {

    /**
     * Public access modifier is still required
     */
    @org.junit.Test
    public void junit4Test() {
        Assert.assertTrue(true);        // JUnit 4 Assertion
        Assertions.assertTrue(true);    // JUnit 5 Assertion
    }

    @org.junit.jupiter.api.Test
    void junit5Test() {
        Assert.assertTrue(true);        // JUnit 4 Assertion
        Assertions.assertTrue(true);    // JUnit 5 Assertion
    }
}
