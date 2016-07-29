package com.drandarov.junit5.utilizations.benchmarked;

import org.junit.jupiter.api.extension.*;

import java.sql.Date;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

/**
 * Extension, that does the logging for the benchmarks.
 *
 * Created by dmitrij-drandarov on 29.07.2016.
 */
public class BenchmarkExtension implements BeforeAllCallback, BeforeTestExecutionCallback,
        AfterTestExecutionCallback, AfterAllCallback {

    private static final String APD = "\t-\t";

    private static final Map<String, Long> startTime = new HashMap<>();
    private static final DateFormat dtForm = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);


    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long start = currentTimeMillis();

        System.out.println("#### Summary           \t" + APD + disp + " ####");
        System.out.println("#### Start of Benchmark\t" + APD + disp + APD + dtForm.format(new Date(start)) + " ####");
        startTime.put(disp, start);
    }

    @Override
    public void beforeTestExecution(TestExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long start = currentTimeMillis();

        System.out.println("#### Method-Benchm. ####" + APD + disp + APD + dtForm.format(new Date(start)));
        startTime.put(context.getDisplayName(), start);
    }

    @Override
    public void afterTestExecution(TestExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long end = currentTimeMillis();

        System.out.println("#### Summary        ####" + APD + disp);
        System.out.println("#### Start          ####" + APD + dtForm.format(new Date(startTime.get(disp))));
        System.out.println("#### End            ####" + APD + dtForm.format(new Date(end)));
        System.out.println("#### Duration       ####" + APD + (end - startTime.get(disp)) + " ms\n");
    }

    @Override
    public void afterAll(ContainerExtensionContext context) throws Exception {
        String disp = context.getDisplayName();
        long end = currentTimeMillis();

        System.out.println("#### End of Benchmark  \t" + APD + disp + APD + dtForm.format(new Date(end)) + " ####");
        System.out.println("#### Duration for class\t" + APD + disp + APD + (end - startTime.get(disp)) + " ms ####");
    }

}