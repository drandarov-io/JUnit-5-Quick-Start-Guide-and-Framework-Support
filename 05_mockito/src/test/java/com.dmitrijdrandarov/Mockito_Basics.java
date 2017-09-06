package com.dmitrijdrandarov;

import com.dmitrijdrandarov.entities.DummyFruit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class Mockito_Basics {

    @Test
    void basic() {
        DummyFruit redBanana = Mockito.mock(DummyFruit.class);
        redBanana.setName("Red Banana");
        redBanana.setType(DummyFruit.TYPE.BANANA);

        Mockito.when(redBanana.getType()).thenReturn(DummyFruit.TYPE.APPLE);

        Assertions.assertEquals(DummyFruit.TYPE.APPLE, redBanana.getType());
    }
}
