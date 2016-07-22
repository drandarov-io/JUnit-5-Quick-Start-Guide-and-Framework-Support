package com.drandard.changes;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Right now the main package to work with is @{@link org.junit.jupiter.api}
 *
 * Created by drandard on 21.07.2016.
 */
public class JUnit5_NameChanges {

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
    void disabled() {}

    /**
     * Assertion Methods are now in class {@link Assertions}.
     */
    @Test
    void assertions() {
        Assertions.assertTrue(true);  //Without static import
        assertTrue(true);             //With static import on org.junit.jupiter.api.Assertions.assertTrue()
    }

}
