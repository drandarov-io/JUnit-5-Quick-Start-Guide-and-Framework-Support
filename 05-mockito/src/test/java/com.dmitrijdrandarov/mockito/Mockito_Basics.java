package com.dmitrijdrandarov.mockito;

import com.dmitrijdrandarov.entities.DummyFruit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 * TODO
 */
class Mockito_Basics {

    @Test
    @Disabled("Incompatible with JDK 12")
    void basic() {
        DummyFruit redBanana = Mockito.mock(DummyFruit.class);
        redBanana.setName("Red Banana");
        redBanana.setType(DummyFruit.TYPE.BANANA);

        Mockito.when(redBanana.getType()).thenReturn(DummyFruit.TYPE.APPLE);

        Assertions.assertEquals(DummyFruit.TYPE.APPLE, redBanana.getType());
    }
}
