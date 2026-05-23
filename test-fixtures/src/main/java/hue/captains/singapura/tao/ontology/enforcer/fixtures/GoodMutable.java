package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Mutable;

/**
 * A well-behaved Mutable: does not implement Immutable.
 */
public final class GoodMutable implements Mutable {

    private String name;

    public String name() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
