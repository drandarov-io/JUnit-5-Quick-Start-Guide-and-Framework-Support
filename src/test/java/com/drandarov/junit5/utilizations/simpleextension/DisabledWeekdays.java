package com.drandarov.junit5.utilizations.simpleextension;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * A simple annotation to retain information about weekdays that the annotated tests are disabled on.
 * Used by {@link DisabledOnWeekday}-Extension.
 *
 * Created by dmitrij-drandarov on 28.07.2016.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface DisabledWeekdays {

    int[] value();

}
