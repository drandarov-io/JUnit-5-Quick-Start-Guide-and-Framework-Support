package com.dmitrijdrandarov.junit5.utils.benchmarked;

import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

/**
 * Extension, that does the logging for the benchmarks. (Implementation is not accurate or performant!)
 *
 * @author dmitrij-drandarov
 * @since 29 Jul 2016
 */
public class BenchmarkExtension implements BeforeAllCallback, BeforeTestExecutionCallback, AfterTestExecutionCallback, AfterAllCallback {

    private static final Logger LOG = LoggerFactory.getLogger(BenchmarkExtension.class);

    private static final String APD = "\t-\t";

    private static final Map<String, Long> startTime = new HashMap<>();
    private static final DateFormat dtForm = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long start = currentTimeMillis();

        LOG.info("#### Summary           \t" + APD + disp + " ####");
        LOG.info("#### Start of Benchmark\t" + APD + disp + APD + dtForm.format(new Date(start)) + " ####");
        startTime.put(disp, start);
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long start = currentTimeMillis();

        LOG.info("#### Method-Benchm. ####" + APD + disp + APD + dtForm.format(new Date(start)));
        startTime.put(context.getDisplayName(), start);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long end = currentTimeMillis();

        LOG.info("#### Summary        ####" + APD + disp);
        LOG.info("#### Start          ####" + APD + dtForm.format(new Date(startTime.get(disp))));
        LOG.info("#### End            ####" + APD + dtForm.format(new Date(end)));
        LOG.info("#### Duration       ####" + APD + (end - startTime.get(disp)) + " ms\n");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long end = currentTimeMillis();

        LOG.info("#### End of Benchmark  \t" + APD + disp + APD + dtForm.format(new Date(end)) + " ####");
        LOG.info("#### Duration for class\t" + APD + disp + APD + (end - startTime.get(disp)) + " ms ####");
    }

}