package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * A well-behaved ValueObject: has fields, overrides equals and hashCode, all immutable.
 */
public final class GoodValueObject implements ValueObject {

    private final String name;
    private final int value;

    public GoodValueObject(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public String name() {
        return name;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof GoodValueObject that)) return false;
        return value == that.value && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
