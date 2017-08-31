package com.dmitrijdrandarov.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Dummy-Pojo-Object extended.
 *
 * @author dmitrij-drandarov
 * @since 14 Feb 2017
 */
@Entity
@Table(name = "DUMMYFRUIT")
public class DummyFruit extends DummyObject {

    public enum TYPE {
        ORANGE, APPLE, BANANA
    }

    @Enumerated(EnumType.STRING)
    private TYPE type;

    public DummyFruit() {
    }

    public DummyFruit(Long id, String name, String description, double value, TYPE type) {
        super(id, name, description, value);
        this.type = type;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return Stream.of(super.toString(), type).map(Object::toString).collect(Collectors.joining(", "));
    }
}
