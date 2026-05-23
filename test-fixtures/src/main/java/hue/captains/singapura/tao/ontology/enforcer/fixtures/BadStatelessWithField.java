package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Stateless;

/**
 * Violates Stateless: has an instance field.
 */
public final class BadStatelessWithField implements Stateless {

    private final String name = "oops";

    public String name() {
        return name;
    }
}
