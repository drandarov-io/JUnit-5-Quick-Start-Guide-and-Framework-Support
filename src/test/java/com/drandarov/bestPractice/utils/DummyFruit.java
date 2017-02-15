package com.drandarov.bestPractice.utils;

/**
 * Dummy-Pojo-Object extended.
 *
 * @author dmitrij-drandarov
 * @since 14 Feb 17
 */
public class DummyFruit extends DummyObject {

    public enum TYPE {
        ORANGE, APPLE, BANANA
    }

    private TYPE type;

    public DummyFruit(String name, String description, double value, TYPE type) {
        super(name, description, value);
        this.type = type;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
