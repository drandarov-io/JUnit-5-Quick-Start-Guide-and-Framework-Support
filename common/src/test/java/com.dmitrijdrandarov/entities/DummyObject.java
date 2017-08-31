package com.dmitrijdrandarov.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Dummy-Pojo-Object.
 *
 * @author dmitrij-drandarov
 * @since 14 Feb 2017
 */
@MappedSuperclass
public class DummyObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double value;

    public DummyObject() {
    }

    public DummyObject(Long id, String name, String description, double value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return Stream.of(id, name, description, value).map(Object::toString).collect(Collectors.joining(", "));
    }
}
