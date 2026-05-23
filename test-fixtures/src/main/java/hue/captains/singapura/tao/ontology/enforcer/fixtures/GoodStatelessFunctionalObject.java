package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/**
 * A well-behaved StatelessFunctionalObject: no fields, pure functions.
 */
public final class GoodStatelessFunctionalObject implements StatelessFunctionalObject {

    public String greet(final String name) {
        return "Hello, " + name;
    }
}
