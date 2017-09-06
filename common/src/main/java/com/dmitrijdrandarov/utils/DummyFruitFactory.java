package com.dmitrijdrandarov.utils;

import com.dmitrijdrandarov.entities.DummyFruit;

import java.util.ArrayList;
import java.util.List;

public class DummyFruitFactory {

    //------------------------------------------------------------------------------------------------------------------------
    //    Single fruits
    //------------------------------------------------------------------------------------------------------------------------

    public DummyFruit createBabyBanana() {
        return new DummyFruit(1L, DummyFruit.TYPE.BANANA, "Baby Banana", "It's yellow!", 20.0);
    }

    public DummyFruit createGrannySmithApple() {
        return new DummyFruit(2L, DummyFruit.TYPE.APPLE, "Granny Smith Apple", "Delicious!", 10.5);
    }

    public DummyFruit createGrapefruit() {
        return new DummyFruit(3L, DummyFruit.TYPE.ORANGE, "Grapefruit", "It's totally an orange, baka!", 8.5);
    }

    public DummyFruit createRedBanana() {
        return new DummyFruit(4L, DummyFruit.TYPE.BANANA, "Red Banana", "I'm not sure!", 60.0);
    }


    //------------------------------------------------------------------------------------------------------------------------
    //    Fruit lists
    //------------------------------------------------------------------------------------------------------------------------

    /**
     * @return List with {@link #createBabyBanana()}, {@link #createGrannySmithApple()}, {@link #createGrapefruit()}
     */
    public List<DummyFruit> createDummyFruits () {
        List<DummyFruit> dummyFruits = new ArrayList<>();
        dummyFruits.add(createBabyBanana());
        dummyFruits.add(createGrannySmithApple());
        dummyFruits.add(createGrapefruit());

        return dummyFruits;
    }

    /**
     * @return List with {@link #createBabyBanana()}, {@link #createGrannySmithApple()}, {@link #createGrapefruit()}, {@link #createRedBanana()}
     */
    public List<DummyFruit> createDummyFruits2() {
        List<DummyFruit> dummyFruits = new ArrayList<>(createDummyFruits());
        dummyFruits.add(createRedBanana());

        return dummyFruits;
    }

}
