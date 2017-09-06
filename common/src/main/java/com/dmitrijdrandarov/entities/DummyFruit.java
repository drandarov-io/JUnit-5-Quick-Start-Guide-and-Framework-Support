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

    public DummyFruit(Long id, TYPE type, String name, String description, Double value) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        DummyFruit that = (DummyFruit) o;

        return that.getValue().compareTo(getValue()) == 0
                && (getId() != null ? getId().equals(that.getId()) : that.getId() == null)
                && (getName() != null ? getName().equals(that.getName()) : that.getName() == null)
                && (getDescription() != null ? getDescription().equals(that.getDescription()) : that.getDescription() == null)
                && type == that.getType();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        temp = Double.doubleToLongBits(getValue());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
