package com.dmitrijdrandarov;

import com.dmitrijdrandarov.entities.DummyFruit;
import com.dmitrijdrandarov.entities.DummyObject;
import com.dmitrijdrandarov.utils.DummyFruitFactory;
import org.assertj.core.api.Condition;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class AssertJ_02_FluentAssertionsAndMoreFeatures {

    private static final Logger LOG = LoggerFactory.getLogger(AssertJ_01_Comparison.class);

    private List<DummyFruit> dummyFruits;
    private DummyFruit babyBanana;
    private DummyFruit grannySmithApple;
    private DummyFruit grapefruit;

    private List<DummyFruit> otherDummyFruitList;
    private DummyFruit redBanana;

    @BeforeEach
    void setUp() {
        DummyFruitFactory dummyFruitFactory = new DummyFruitFactory();
        dummyFruits = dummyFruitFactory.createDummyFruits();
        otherDummyFruitList = dummyFruitFactory.createDummyFruits2();

        babyBanana = dummyFruitFactory.createBabyBanana();
        grannySmithApple = dummyFruitFactory.createGrannySmithApple();
        grapefruit = dummyFruitFactory.createGrapefruit();
        redBanana = dummyFruitFactory.createRedBanana();
    }


    //------------------------------------------------------------------------------------------------------------------------
    //    Fluent assertions
    //------------------------------------------------------------------------------------------------------------------------

    @Test
    void fluentAssertionsWithDifferentTypes() {

        // Very readable, but a bit too much overhead for this use case
        assertThat(babyBanana.getName()).isEqualTo("Baby Banana");    // StringAssert
        assertThat(babyBanana).isNotEqualTo(grannySmithApple);        // ObjectAssert


        // Different AssertTypes

        // Multiple string-specific assertions chained together
        assertThat(babyBanana.getName())
                .startsWith("Bab")
                .endsWith("nana")
                .isEqualToIgnoringCase("baby banana");

        // Multiple collection-specific assertions chained together
        assertThat(dummyFruits)
                .hasSize(3)
                .contains(babyBanana, grapefruit)
                .doesNotContain(redBanana)
                .hasOnlyElementsOfType(DummyFruit.class);

        assertThat(otherDummyFruitList)
                .containsAll(dummyFruits);

        // Collection-specific assertions with custom conditions
        assertThat(dummyFruits)
                .allMatch(dummyFruit -> dummyFruit.getName().contains("a"));

        assertThat(dummyFruits)
                .areExactly(2, new Condition<>(dummyFruit -> dummyFruit.getName().contains("t"), "contains t"));

        assertThat(dummyFruits)
                .extracting(DummyObject::getName)
                .areExactly(2, new Condition<>(dummyName -> dummyName.contains("t"), "contains t"));

        // Temporal-specific assertions chained together
        Instant instant = Instant.now();
        Instant inOneMinute = instant.plusSeconds(60);
        Instant beforeOneMinute = instant.minusSeconds(60);
        assertThat(instant)
                .isBetween(instant.minusSeconds(1), instant.plusSeconds(1))
                .isBetween(instant.minusSeconds(1), inOneMinute)
                .isBetween(beforeOneMinute, instant.plusSeconds(1))
                .isBetween(beforeOneMinute, inOneMinute);

    }


    //------------------------------------------------------------------------------------------------------------------------
    //    Working with properties/fields
    //------------------------------------------------------------------------------------------------------------------------

    @Test
    void workingWithProperties() {
        // Check if list contains elements with specific properties (Java 7 reflection)
        assertThat(dummyFruits)
                .extracting("name")
                .containsExactly("Baby Banana", "Granny Smith Apple", "Grapefruit");

        // Check if list contains elements with specific properties (Java 8 method reference)
        assertThat(dummyFruits)
                .extracting(DummyFruit::getName)
                .doesNotContain("Red Banana");

        // Extract multiple values
        assertThat(dummyFruits).extracting(DummyFruit::getName, DummyFruit::getType)
                .containsExactly(
                        tuple("Baby Banana", DummyFruit.TYPE.BANANA),
                        tuple("Granny Smith Apple", DummyFruit.TYPE.APPLE),
                        tuple("Grapefruit", DummyFruit.TYPE.ORANGE));
    }


    //------------------------------------------------------------------------------------------------------------------------
    //    Working with filtering
    //------------------------------------------------------------------------------------------------------------------------

    @Test
    void workingWithFiltering() {
        // Filtering Examples
        assertThat(dummyFruits)
                .filteredOn(fruit -> fruit.getName().contains("t"))
                .containsOnly(grapefruit, grannySmithApple);

        assertThat(otherDummyFruitList)
                .filteredOn(fruit -> fruit.getName().contains("Bana"))
                .containsExactly(babyBanana, redBanana)
                .extracting(DummyFruit::getType)
                .containsOnly(DummyFruit.TYPE.BANANA);
    }


    //------------------------------------------------------------------------------------------------------------------------
    //    Working with exceptions
    //------------------------------------------------------------------------------------------------------------------------

    @Test
    void workingWithExceptions() {
        // Alternative to assertThrows of JUnit 5 with fluent api
        assertThatThrownBy(() -> { throw new Exception("I'm an exception!"); }).hasMessage("I'm an exception!");

        // Or get the Throwable as an instance
        Throwable thrown = catchThrowable(() -> { throw new Exception("I'm an exception!"); });
        assertThat(thrown).hasMessageContaining("I'm an exception!");    // ThrowableAssert
    }


    //------------------------------------------------------------------------------------------------------------------------
    //    Soft Assertions
    //------------------------------------------------------------------------------------------------------------------------

    /**
     * SoftAssertions are used to group assertions with different assertion bases and force all of them to run even if a previous assertion failed.
     */
    @Test
    void softAssertion() {
        SoftAssertions.assertSoftly(soft -> {
            // Base dummyFruits
            soft.assertThat(dummyFruits)
                    .hasSize(3)
                    .containsExactly(babyBanana, grannySmithApple, grapefruit);
            // Base otherDummyFruitList
            soft.assertThat(otherDummyFruitList)
                    .hasSize(4)
                    .containsAll(dummyFruits)
                    .containsExactly(babyBanana, grannySmithApple, grapefruit, redBanana);
            // Base
            soft.assertThat(grapefruit).extracting(DummyFruit::getType).allMatch(DummyFruit.TYPE.ORANGE::equals);
        });
    }


    //------------------------------------------------------------------------------------------------------------------------
    //    Descriptions
    //------------------------------------------------------------------------------------------------------------------------

    @Test
    void descriptionsWithAs() {
        // Description with .as() will be shown before the error message
        assertThat(babyBanana.getValue()).as("check %s's value", babyBanana.getName()).isEqualTo(20.0);
    }
}
