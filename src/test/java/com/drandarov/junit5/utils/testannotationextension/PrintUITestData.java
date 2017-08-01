package com.drandarov.junit5.utils.testannotationextension;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.AnnotatedElement;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Prints some information about the Test annotated by @{@link UITest}.
 *
 * @author dmitrij-drandarov
 * @since 29 Jul 2016
 */
class PrintUITestData implements BeforeEachCallback {

    private static final Logger LOG = Logger.getGlobal();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Optional<AnnotatedElement> contextElement = context.getElement();
        AnnotatedElement annotatedElement = contextElement.orElse(null);

        if (annotatedElement != null) {
            UITest uiTest = annotatedElement.getAnnotation(UITest.class);
            LOG.info("Doing some setup for " + uiTest.value());
        }

    }

}
