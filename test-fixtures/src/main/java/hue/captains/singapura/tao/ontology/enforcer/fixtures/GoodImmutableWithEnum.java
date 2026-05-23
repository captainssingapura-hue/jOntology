package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Immutable;

/**
 * A well-behaved Immutable with an enum field — tests that enum types
 * are recognized as immutable.
 */
public final class GoodImmutableWithEnum implements Immutable {

    private final String name;
    private final Color color;

    public GoodImmutableWithEnum(final String name, final Color color) {
        this.name = name;
        this.color = color;
    }

    public String name() {
        return name;
    }

    public Color color() {
        return color;
    }
}
