package com.drandarov.junit5.utilizations.testannotationextension;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.TestExtensionContext;

import java.lang.reflect.AnnotatedElement;
import java.util.Optional;

/**
 * Prints some information about the Test annotated by @{@link UITest}.
 *
 * Created by dmitrij-drandarov on 29.07.2016.
 */
public class PrintUITestData implements BeforeEachCallback {

    @Override
    public void beforeEach(TestExtensionContext context) throws Exception {
        Optional<AnnotatedElement> contextElement = context.getElement();
        UITest uiTest  = contextElement.get().getAnnotation(UITest.class);

        System.out.println("Doing some setup for " + uiTest.value());
    }

}
