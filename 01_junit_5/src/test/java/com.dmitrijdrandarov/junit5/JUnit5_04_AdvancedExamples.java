package com.dmitrijdrandarov.junit5;

import com.dmitrijdrandarov.junit5.utils.benchmarked.BenchmarkExtension;
import com.dmitrijdrandarov.junit5.utils.benchmarked.Benchmarked;
import com.dmitrijdrandarov.junit5.utils.simpleextension.DisabledOnWeekday;
import com.dmitrijdrandarov.junit5.utils.simpleextension.DisabledWeekdays;
import com.dmitrijdrandarov.junit5.utils.testannotationextension.UITest;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A class containing advanced test samples and utils of features introduced and explained before.
 *
 * @author dmitrij-drandarov
 * @since 01 Aug 2016
 */
class JUnit5_04_AdvancedExamples {

    private static final Logger LOG = LoggerFactory.getLogger(JUnit5_04_AdvancedExamples.class);

    /*##############################################
    #           Advanced Examples
    ##############################################*/

    /**
     * Here I go a step further and annotate my days dynamically, by specifying the days I don't want the test to run
     * on with another custom annotation called @{@link DisabledWeekdays}.
     *
     * My extension {@link DisabledOnWeekday} later searches for @{@link DisabledWeekdays} and determines whether the
     * test should run or not.
     */
    @Test
    @DisabledWeekdays({Calendar.THURSDAY, Calendar.SATURDAY})
    @ExtendWith(DisabledOnWeekday.class)
    void disabledOnWeekdaysTest() {}

    /**
     * Here I use an annotation @{@link UITest} that is annotated by @{@link Test} itself, so it will be executed
     * properly. @{@link UITest} contains grouped information and annotations about this test like predefined
     * extensions. Further information in @{@link UITest}s JavaDoc.
     *#
     * This of course could be also possible for the examples above.
     */
    @UITest("/fxml/sample.fxml")
    void userInterfaceTest(Pane root) {
        LOG.info(String.valueOf(root.getPrefWidth()));     // 555.0 (defined in FXML-File)
        LOG.info(String.valueOf(root.getPrefHeight()));    // 333.0 (defined in FXML-File)
    }

    /**
     * For this example I wrote an annotation @{@link Benchmarked} that doesn't include @{@link Test} - which it could -
     * but instead only contains an self-written extension called {@link BenchmarkExtension}. Annotating your class
     * with this will basically provide you with automatic benchmarking.
     *
     * This could of course be also placed on top of the class.
     */
    @Test
    @Benchmarked
    void benchmarkedTest() {
        LOG.info("Calculating some primes...");
        int primeCount = 200000;

        assertEquals(primeCount, IntStream.iterate(2, i -> i + 1)
                .filter(i -> LongStream.rangeClosed(2, (long) (Math.sqrt(i))).allMatch(n -> i % n != 0))
                .limit(primeCount).toArray().length);
    }
}
