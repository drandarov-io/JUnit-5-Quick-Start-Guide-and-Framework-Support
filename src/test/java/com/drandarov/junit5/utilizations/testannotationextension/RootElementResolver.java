package com.drandarov.junit5.utilizations.testannotationextension;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import org.junit.jupiter.api.extension.*;

import java.io.IOException;

/**
 * Resolves a Pane Parameter by loading it from the fxml-path in {@link UITest#value()}
 *
 * Created by dmitrij-drandarov on 29.07.2016.
 */
public class RootElementResolver implements ParameterResolver {

    @Override
    public boolean supports(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getTags().contains("userInterface")
                & parameterContext.getParameter().getType().equals(Pane.class);
    }

    @Override
    public Object resolve(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        UITest annotation = extensionContext.getElement().get().getAnnotation(UITest.class);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(annotation.value()));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pane;
    }
}
