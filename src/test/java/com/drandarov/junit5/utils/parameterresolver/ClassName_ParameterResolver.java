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
public class ClassName_ParameterResolver implements ParameterResolver {

    /**
     * Simple example that only checks if the Parameter-Type is a {@link String} based on the Parameter-Context to
     * determine whether the Parameter is supported by this {@link ParameterResolver}.
     */
    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(String.class);
    }

    /**
     * Simple example that simply resolves the Parameter by returning the Class-Name based on the Parameter-Context.
     */
    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Class<?> contextClass = extensionContext.getTestClass().orElse(null);
        return contextClass == null ? null : contextClass.getSimpleName();
    }

}
