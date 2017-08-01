package com.drandarov.bestPractice;

import com.drandarov.bestPractice.utils.DummyFruit;
import com.drandarov.bestPractice.utils.DummyUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.HashMap;
import java.util.Map;

import static com.drandarov.bestPractice.utils.DummyFruit.TYPE;
import static org.junit.jupiter.api.Assertions.*;

/**
 * A demo-class with a collection of simple best-practices when writing JUnit-Tests.
 *
 * @author dmitrij-drandarov
 * @since 10 Feb 2017
 */
class JUnit_BestPractice {

    private Map<Integer, DummyFruit> dummyFruits;

    @BeforeEach
    void dummy() {
        dummyFruits = new HashMap<>();
        dummyFruits.put(1, new DummyFruit("Baby Banana", "It's yellow!", 20.0, TYPE.BANANA));
        dummyFruits.put(2, new DummyFruit("Granny Smith Apple", "Delicious!", 10.5, TYPE.APPLE));
        dummyFruits.put(3, new DummyFruit("Grapefruit", "It's totally an orange, baka!", 8.5, TYPE.ORANGE));
    }

    /*##############################################
    #           Choosing the right assertion-type
    ##############################################*/

    /**
     * Don't check for nulls with {@link Assertions#assertTrue}. Error messages will be meaningless and therefore useless.
     * Do use {@link Assertions#assertNotNull} or {@link Assertions#assertNull} instead.
     */
    @Test
    void assertTrueNotNullTest() {
        // Unclear, useless error-message when tests fail
        assertThrows(AssertionFailedError.class, () ->
                assertTrue(dummyFruits.get(4) != null)).printStackTrace();

        // org.opentest4j.AssertionFailedError // It couldn't be more useless...
    }

    @Test
    void assertNotNullTest() {
        // Better error-message you can actually use and understand
        assertThrows(AssertionFailedError.class, () ->
                assertNotNull(dummyFruits.get(4))).printStackTrace();

        // org.opentest4j.AssertionFailedError: expected: not <null>
    }

    /**
     * The same applies for using {@link Assertions#assertTrue} and {@link Object#equals}. Use {@link Assertions#assertEquals} instead.
     */
    @Test
    void assertTrueEqualsTest() {
        // Unclear, useless error-message when tests fail
        assertThrows(AssertionFailedError.class, () ->
                assertTrue(TYPE.BANANA.equals(dummyFruits.get(2).getType()))).printStackTrace();

        // org.opentest4j.AssertionFailedError: // It - again - couldn't be more useless...
    }

    @Test
    void assertEqualsTest() {
        // Really useful error-message you can actually use and understand
        assertThrows(AssertionFailedError.class, () ->
            assertEquals(TYPE.BANANA, dummyFruits.get(2).getType())).printStackTrace();

        // org.opentest4j.AssertionFailedError: expected: <BANANA> but was: <APPLE>
    }

    /*##############################################
    #           Working with delta
    ##############################################*/

    /**
     * It may seem trivial, but they happen more often than you would assume.
     */
    @Test
    void wrongDelta() {
        assertNotEquals(0.9, DummyUtil.calculateTimesThree(0.3)); // --> That's why the delta is important

        // Using a 0.0 delta doesn't make much sense and JUnit 5 actively prevents it
        assertThrows(AssertionFailedError.class, () ->
                assertEquals(20.9, DummyUtil.calculateTimesThree(19), 0.0)).printStackTrace();

        // With such a delta the correctness of the calculation is no longer assured
        assertEquals(15.5, DummyUtil.calculateTimesThree(5.0), 0.5);
    }

    /**
     * You should use a very small delta, since the variations caused by using doubles are mostly as small as
     * 0.0000000000004.
     */
    @Test
    void betterDelta() {
        assertEquals(15.0, DummyUtil.calculateTimesThree(5.0), 0.0000000001);
    }

    /**
     * Starting with JUnit 5 you no longer have to necessarily declare the delta, since it will be checked by the framework.
     * For implementation details look in {@link org.junit.jupiter.api.AssertionUtils#doublesAreEqual(double, double)}.
     *
     * If you use the delta for rounding mistakes, really think about it: You 'actually' calculate rounded values, but
     * you 'expect' not rounded ones, so you just use a delta as a workaround? Really?! That's not how you check for
     * 'equality'!
     */
    @Test
    void idealDelta() {
        assertEquals(15.0, DummyUtil.calculateTimesThree(5.0));
    }

    /*##############################################
    #           Correct parameter order
    ##############################################*/

    @Test
    void wrongOrderTest() {
        //Gives you unclear, actually wrong fail-reports!
        assertThrows(AssertionFailedError.class, () ->
                assertEquals(dummyFruits.get(2).getName(), "Grapefruit")).printStackTrace();
        // . . . . . . . . . . ^expected . . . . . . . . . . // ^actual

        // org.opentest4j.AssertionFailedError: expected: <Granny Smith Apple> but was: <Grapefruit>

        /* "So wait, the constant String I put in there is not an expected value? Tell me more about that!
            Or don't... Just fix the order and everything will be fine ;)" */
    }

    @Test
    void correctOrderTest() {
        // Clear and useful fail-report ;)
        assertThrows(AssertionFailedError.class, () ->
                assertEquals("Grapefruit", dummyFruits.get(2).getName())).printStackTrace();
        // . . . . . . . . . . ^expected . . . . . ^actual

        // org.opentest4j.AssertionFailedError: expected: <Grapefruit> but was: <Granny Smith Apple>

        /* "Oh, my actual value is not what was expected? Well now I know what the problem is and I can fix it!" */
    }

}
