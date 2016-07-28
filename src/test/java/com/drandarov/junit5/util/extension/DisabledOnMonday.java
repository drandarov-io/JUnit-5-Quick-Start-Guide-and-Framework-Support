package com.drandarov.junit5.util.extension;

import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.TestExecutionCondition;
import org.junit.jupiter.api.extension.TestExtensionContext;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

/**
 * An extension that disables this test class on Mondays, because nobody likes those, right?
 *
 * Created by dmitrij-drandarov on 28.07.2016.
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
