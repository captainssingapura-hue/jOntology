package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.ValueObject;

/**
 * Violates ValueObject: does not override equals or hashCode.
 */
public final class BadValueObjectNoEquals implements ValueObject {

    private final String name;

    public BadValueObjectNoEquals(final String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}
