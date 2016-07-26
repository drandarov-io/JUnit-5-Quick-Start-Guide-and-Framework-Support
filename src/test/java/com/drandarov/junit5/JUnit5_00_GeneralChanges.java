package com.drandarov.junit5;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by drandard on 21.07.2016.
 */
public class JUnit5_00_GeneralChanges {

    /*
    ##################################################################################################################
                                                      Test-Methods
    ##################################################################################################################
    */

    /**
     * Tests are now only not allowed to be static / private. Same goes for @Before.../@After...
     *
     * timeout=? and expected=? functionality has now moved elsewhere. See in {@link JUnit5_01_NewFeatures}
     */
    @Test
    void testTest() {}


    /*
    ##################################################################################################################
                                                      Annotations
    ##################################################################################################################
    */

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
     * Annotation @Ignore was replaced by @{@link Disabled}.
     * Sounds less negative.
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


    /*
    ##################################################################################################################
                                                      Assertions
    ##################################################################################################################
    */

    /**
     * Assertion Methods are now in class {@link Assertions}. Method names stayed mostly the same otherwise.
     */
    @Test
    void assertionsTest() {
        Assertions.assertTrue(true); // Without static import
        assertTrue(true);            // With static import on org.junit.jupiter.api.Assertions.assertTrue()
    }


}
