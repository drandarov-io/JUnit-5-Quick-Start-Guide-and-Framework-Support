package com.drandarov.junit5.utils.testannotationextension;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.util.function.Supplier;

/**
 * Resolves a Pane Parameter by loading it from the fxml-path in {@link UITest#value()}
 *
 * @author dmitrij-drandarov
 * @since 29 Jul 2016
 */
class RootElementResolver implements ParameterResolver {

    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getTags().contains("userInterface")
                & parameterContext.getParameter().getType().equals(Pane.class);
    }

    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        AnnotatedElement annotatedElement = extensionContext.getElement().orElse(null);

        Supplier<Pane> getPane = () -> {
            UITest annotation = annotatedElement.getAnnotation(UITest.class);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(annotation.value()));
            try { return loader.load(); }
            catch (IOException e) { return null; }
        };

        return annotatedElement == null ? null : getPane;
    }
}
