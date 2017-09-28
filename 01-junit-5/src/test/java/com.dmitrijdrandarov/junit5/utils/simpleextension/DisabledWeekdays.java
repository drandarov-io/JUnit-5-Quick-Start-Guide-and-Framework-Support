package com.dmitrijdrandarov.junit5.utils.simpleextension;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A simple annotation to retain information about weekdays that the annotated tests are disabled on.
 * Used by {@link DisabledOnWeekday}-Extension.
 *
 * @author dmitrij-drandarov
 * @since 28 Jul 2016
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DisabledWeekdays {
    int[] value();
}
