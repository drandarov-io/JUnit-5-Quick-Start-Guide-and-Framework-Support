package com.drandarov.junit5.utils.simpleextension;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.TestExecutionCondition;
import org.junit.jupiter.api.extension.TestExtensionContext;

import java.util.Calendar;

/**
 * An extension that disables a test class on Mondays, because nobody likes those, right?
 *
 * @author dmitrij-drandarov
 * @since 28 Jul 2016
 */
public class DisabledOnMonday implements TestExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluate(TestExtensionContext context) {
        boolean monday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;

        return monday ?
                ConditionEvaluationResult.disabled("I spare you on Mondays.")
                :
                ConditionEvaluationResult.enabled("Don't spare you on other days though >:(");
    }

}
