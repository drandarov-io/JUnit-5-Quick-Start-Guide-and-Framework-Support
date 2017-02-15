package com.drandarov.junit5.utils.parameterresolver;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * See {@link ParameterResolver}-JavaDoc.
 *
 * @author dmitrij-drandarov
 * @since 25 Jul 2016
 */
public class ParameterIndex_ParameterResolver implements ParameterResolver {

    /**
     * Simple example that only checks if the Parameter-Type is a {@link Long} based on the Parameter-Context to
     * determine whether the Parameter is supported by this {@link ParameterResolver}.
     */
    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(Long.class);
    }

    /**
     * Simple example that simply resolves the Parameter by returning the parameterIndex based on the Parameter-Context.
     */
    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return (long) parameterContext.getIndex();
    }

}