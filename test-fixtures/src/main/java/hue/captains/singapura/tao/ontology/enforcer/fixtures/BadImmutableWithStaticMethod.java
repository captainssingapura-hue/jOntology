package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Immutable;

/**
 * Violates Immutable: has a static method (not main).
 */
public final class BadImmutableWithStaticMethod implements Immutable {

    private final String name;

    public BadImmutableWithStaticMethod(final String name) {
        this.name = name;
    }

    public static BadImmutableWithStaticMethod of(final String name) {
        return new BadImmutableWithStaticMethod(name);
    }

    public String name() {
        return name;
    }
}
