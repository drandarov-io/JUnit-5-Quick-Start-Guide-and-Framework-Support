package com.drandard.features;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Created by drandard on 22.07.2016.
 */
public class JUnit5_NewGeneralFeatures {

    /**
     * Tests can now receive Display-Names via @{@link DisplayName}.
     * These are e.g. used by the IDE, Console or with Test-Paramets adressed later.
     */
    @Test
    @DisplayName("Parameter-Test")
    void displayNameTest() {}

//    @Tag()
//    @Tags()
//    @ExcludeTags()
//    @TestFactory
//    @ParametersSuppliedBy()
//    @ParametersNames()

}
