package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Immutable;

import java.util.List;

/**
 * Violates Immutable: has a field of non-immutable type (List).
 */
public final class BadImmutableWithMutableField implements Immutable {

    private final List<String> items;

    public BadImmutableWithMutableField(final List<String> items) {
        this.items = items;
    }

    public List<String> items() {
        return items;
    }
}
