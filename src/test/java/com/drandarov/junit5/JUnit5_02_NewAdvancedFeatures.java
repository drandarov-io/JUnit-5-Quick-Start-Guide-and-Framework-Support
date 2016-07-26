package com.drandarov.junit5;

import com.drandarov.junit5.util.LongParameterResolver;
import com.drandarov.junit5.util.StringParameterResolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.*;

/**
 * A collection of new, advanced features introduced in JUnit 5.
 *
 * Created by drandard on 22.07.2016.
 */
public class JUnit5_02_NewAdvancedFeatures {

    /*
    ##################################################################################################################
                                                    Test-Extensions
    ##################################################################################################################
    */


    /*
    ##################################################################################################################
                                                    Test-Parameters
    ##################################################################################################################
    */

    /**
     * Tests can now be provided with parameters. Those are resolved by {@link ParameterResolver}-Implementations.
     * This enables dependency injection at method level.
     *
     * Resolvers for {@link TestInfo} and {@link TestReporter} are already provided. Other parameters require your own
     * {@link ParameterResolver}-Implementations to be added with the @{@link ExtendWith}-Annotation.
     *
     * @param testInfo Information about the current test
     * @param testReporter TODO: ?!
     */
    @Test
    void parameterTest(TestInfo testInfo, TestReporter testReporter) {
        System.out.println("DisplayName:\t" + testInfo.getDisplayName());
        System.out.println("Tags:\t\t\t" + testInfo.getTags());
        System.out.println("TestClass:\t\t" + testInfo.getTestClass());
        System.out.println("TestMethod:\t\t" + testInfo.getTestMethod());

        //TODO: What is this?
        testReporter.publishEntry("parameterTestTime", Long.toString(System.currentTimeMillis()));
    }

    /**
     * A simple example of a custom parameter. @{@link ExtendWith} is used to mark {@link StringParameterResolver} as
     * used {@link ParameterResolver}
     *
     * @param parameterString String-Parameter that will be injected by {@link StringParameterResolver}
     * @param parameterLong Long-Parameter that will be injected by {@link LongParameterResolver}
     */
    @Test
    @ExtendWith({StringParameterResolver.class, LongParameterResolver.class})
    void customParameterTest(String parameterString, Long parameterLong) {
        System.out.println(parameterString);
        System.out.println(parameterLong);
    }

}
