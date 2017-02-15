package com.drandarov.junit5.utils.testannotationextension;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.io.IOException;
import java.lang.reflect.AnnotatedElement;

/**
 * Resolves a Pane Parameter by loading it from the fxml-path in {@link UITest#value()}
 *
 * Created by dmitrij-drandarov on 29.07.2016.
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
        Pane pane = null;

        if (annotatedElement != null) {
            UITest annotation = annotatedElement.getAnnotation(UITest.class);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(annotation.value()));
            try {
                pane = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return pane;
    }
}
