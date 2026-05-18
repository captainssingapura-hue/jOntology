package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Immutable;

/**
 * Violates Immutable: has a setter method.
 */
public final class BadImmutableWithSetter implements Immutable {

    private final String name;

    public BadImmutableWithSetter(final String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public void setName(final String name) {
        // violates immutable contract
    }
}
