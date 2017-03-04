package com.drandarov.junit5.utils.testannotationextension;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Test annotated by this will be executed by the test runner without problems due to @{@link Test} being included.
 * You can basically group annotations by doing this and save some space, by not having to add all those
 * {@link ExtendWith}s etc. to each method.
 * Readability inside the test classes is the key here. And it looks cooler ;)
 *
 * @author dmitrij-drandarov
 * @since 29 Jul 2016
 */
@Test
@Tag("userInterface")                      // For simple identification by ParameterResolvers
@ExtendWith(PrintUITestData.class)         // Prints UI Test Data before each test
@ExtendWith(RootElementResolver.class)     // Resolves the root pane
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)        // Required for the test to be automatically executed
public @interface UITest {

    /**
     * FXML-Path.
     *
     * @return FXML-Path used for the UI-Test.
     */
    String value();

}
