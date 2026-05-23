package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Immutable;

/**
 * A well-behaved Immutable: all fields final, no setters, all field types immutable.
 */
public final class GoodImmutable implements Immutable {

    private final String name;
    private final int value;

    public GoodImmutable(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public String name() {
        return name;
    }

    public int value() {
        return value;
    }
}
