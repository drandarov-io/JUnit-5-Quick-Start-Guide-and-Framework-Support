package com.drandarov.junit5.util;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * See Interface-JavaDoc
 *
 * Created by drandard on 25.07.2016.
 */
public class LongParameterResolver implements ParameterResolver {

    /**
     * Simple example that only checks if the Paramter-Type is a Long based on the Parameter-Context to determine
     * whether the Parameter is supported by this ParameterResolver.
     */
    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(Long.class);
    }

    /**
     * Simple example that simply resolves the Parameter by returning the current millis based on the Parameter-Context.
     */
    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return Long.valueOf(System.currentTimeMillis());
    }

}