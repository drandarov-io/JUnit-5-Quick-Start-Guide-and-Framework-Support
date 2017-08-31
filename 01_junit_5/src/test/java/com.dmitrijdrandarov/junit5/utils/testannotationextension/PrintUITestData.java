package com.dmitrijdrandarov.junit5.utils.testannotationextension;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.AnnotatedElement;
import java.util.Optional;

/**
 * Prints some information about the Test annotated by @{@link UITest}.
 *
 * @author dmitrij-drandarov
 * @since 29 Jul 2016
 */
class PrintUITestData implements BeforeEachCallback {

    private static final Logger LOG = LoggerFactory.getLogger(PrintUITestData.class);

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
