package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * A well-behaved ValueObject that also has a static field.
 * Tests that the field loop correctly skips static fields before finding instance fields.
 */
public final class GoodValueObjectWithStaticField implements ValueObject {

    private static final String DEFAULT = "default";

    private final String name;

    public GoodValueObjectWithStaticField(final String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof GoodValueObjectWithStaticField that)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
