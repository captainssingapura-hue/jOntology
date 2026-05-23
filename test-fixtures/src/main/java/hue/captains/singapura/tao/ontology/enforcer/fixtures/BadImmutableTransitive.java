package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Immutable;

/**
 * Violates Immutable transitively: holds a field whose type (BadImmutableNonFinalField)
 * itself violates the Immutable contract.
 */
public final class BadImmutableTransitive implements Immutable {

    private final BadImmutableNonFinalField nested;

    public BadImmutableTransitive(final BadImmutableNonFinalField nested) {
        this.nested = nested;
    }

    public BadImmutableNonFinalField nested() {
        return nested;
    }
}
