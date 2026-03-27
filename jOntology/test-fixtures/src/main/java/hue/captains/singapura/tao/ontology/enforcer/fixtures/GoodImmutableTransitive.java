package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Immutable;

/**
 * A well-behaved Immutable with a field whose type is also Immutable (transitive).
 */
public final class GoodImmutableTransitive implements Immutable {

    private final GoodImmutable nested;

    public GoodImmutableTransitive(final GoodImmutable nested) {
        this.nested = nested;
    }

    public GoodImmutable nested() {
        return nested;
    }
}
