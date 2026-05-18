package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Immutable;

/**
 * Violates Immutable: has a non-final field.
 */
public final class BadImmutableNonFinalField implements Immutable {

    private String name;

    public BadImmutableNonFinalField(final String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }
}
