package com.drandarov.junit5.util.extension;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ContainerExtensionContext;

/**
 * A simple {@link BeforeAllCallback}-Implementation that prints a string to the console before any tests start.
 *
 * Created by dmitrij-drandarov on 28.07.2016.
 */
public class BeforeAllExtensionImpl implements BeforeAllCallback {

    @Override
    public void beforeAll(ContainerExtensionContext context) throws Exception {
        System.out.println("This is displayed before all Tests when a Test-Class is extendend with this Extension.");
    }

}
