package com.drandarov.junit5.utilizations.parameterresolver;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

/**
 * See {@link ParameterResolver}-JavaDoc.
 *
 * Created by dmitrij-drandarov on 25.07.2016.
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
        return extensionContext.getTestClass().get().getSimpleName();
    }

}
