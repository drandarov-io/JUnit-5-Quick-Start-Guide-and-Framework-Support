package com.dmitrijdrandarov.junit5.utils.simpleextension;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.AnnotatedElement;
import java.util.Calendar;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * An extension that disables this test class on the weekday specified by {@link DisabledWeekdays}.
 *
 * @author dmitrij-drandarov
 * @since 28 Jul 2016
 */
public class DisabledOnWeekday implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {

        // Search for the @DisabledWeekdays annotation from the TestExtensionContext
        Optional<AnnotatedElement> contextElement = context.getElement();
        AnnotatedElement annotatedElement = contextElement.orElse(null);

        if (annotatedElement == null) return null;

        DisabledWeekdays weekdayAnnotation = annotatedElement.getAnnotation(DisabledWeekdays.class);

        // Determine whether the test should be disabled
        boolean weekdayToday = IntStream.of(weekdayAnnotation.value())
                .anyMatch(day -> day == Calendar.getInstance().get(Calendar.DAY_OF_WEEK));

        // Return a ConditionEvaluationResult based on the outcome of the boolean weekdayToday
        return weekdayToday ?
                ConditionEvaluationResult.disabled("I spare you today.") :
                ConditionEvaluationResult.enabled("Don't spare you on other days though >:(");
    }

}
