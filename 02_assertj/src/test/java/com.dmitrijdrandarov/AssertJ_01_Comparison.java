package com.dmitrijdrandarov;

import com.dmitrijdrandarov.entities.DummyFruit;
import com.dmitrijdrandarov.entities.DummyObject;
import com.dmitrijdrandarov.utils.DummyFruitFactory;
import org.assertj.core.api.Assertions;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class AssertJ_01_Comparison {

    private static final Logger LOG = LoggerFactory.getLogger(AssertJ_01_Comparison.class);

    private List<DummyFruit> dummyFruits;

    @BeforeEach
    void setUp() {
        DummyFruitFactory dummyFruitFactory = new DummyFruitFactory();
        dummyFruits = dummyFruitFactory.createDummyFruits();
    }

    /**
     * Hard to read and failed assertion messages are meaningless.
     *
     * Let's not even talk about Java 7 and downwards.
     */
    @Test
    void objectHasPropertyJava8() {
        // Java 8
        assertTrue(dummyFruits.stream().map(DummyObject::getName).anyMatch("Baby Banana"::equals)
                && dummyFruits.stream().map(DummyObject::getName).anyMatch("Granny Smith Apple"::equals)
                && dummyFruits.stream().map(DummyObject::getName).anyMatch("Grapefruit"::equals));


        // Failed assertion message meaningless

        // org.opentest4j.AssertionFailedError:

    }

    /**
     * Better, but still hard to read. Failed assertion messages are ok.
     *
     * Framework hasn't been updated in a while. No longer bundled in JUnit 5 like it was in JUnit 4.
     */
    @Test
    void objectHasPropertyHamcrest() {
        // Hamcrest not type-safe. Hard to infer because of reflections (somewhat adressed in JDK 9)
        MatcherAssert.assertThat(dummyFruits,
                Matchers.anyOf(Matchers.contains( // No type-safety here
                        Matchers.hasProperty("name", Matchers.is("Baby Banana")),
                        Matchers.hasProperty("name", Matchers.is("Granny Smith Apple")),
                        Matchers.hasProperty("name", Matchers.is("Grapefruit")))
        ));

        // Hamcrest, type-safe
        MatcherAssert.assertThat(dummyFruits,
                Matchers.anyOf(Collections.singletonList(Matchers.contains(Arrays.asList( // Safe, but very ugly...
                        Matchers.hasProperty("name", Matchers.is("Baby Banana")),
                        Matchers.hasProperty("name", Matchers.is("Granny Smith Apple")),
                        Matchers.hasProperty("name", Matchers.is("Grapefruit")))
        ))));


        // Hard to read, but at least it states the problem in case assertion fails in a somewhat readable form.

        /*
        java.lang.AssertionError:
        Expected: (iterable containing [hasProperty("name", is "Baby Banana"), hasProperty("name", is "Granny Smith Apple"), hasProperty("name", is "FailedFruit")])
            but: was <[1, Baby Banana, It's yellow!, 20.0, BANANA, 2, Granny Smith Apple, Delicious!, 10.5, APPLE, 3, Grapefruit, It's totally an orange, baka!, 8.5, ORANGE]>
        */
    }

    /**
     * Short, readable, meaningful failed assertion message.
     *
     * Framework is frequently updated.
     */
    @Test
    void objectHasPropertyAssertJ() {
        // AssertJ
        Assertions.assertThat(dummyFruits)
                .extracting(DummyObject::getName)
                .contains("Baby Banana", "Granny Smith Apple", "Grapefruit");


        // Great message in case assertion fails

        /*
        java.lang.AssertionError:
        Expecting:
            <["Baby Banana", "Granny Smith Apple", "Grapefruit"]>
        to contain:
            <["Baby Banana", "Granny Smith Apple", "FailedFruit"]>
        but could not find:
            <["FailedFruit"]>
       */
    }
}
