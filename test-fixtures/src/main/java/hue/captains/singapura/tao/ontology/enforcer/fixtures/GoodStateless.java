package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Stateless;

/**
 * A well-behaved Stateless: no instance fields.
 */
public final class GoodStateless implements Stateless {

    public String greet() {
        return "hello";
    }
}
