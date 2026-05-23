package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

/**
 * Violates StatelessFunctionalObject: has an instance field.
 */
public final class BadStatelessFunctionalObjectWithField implements StatelessFunctionalObject {

    private final String prefix = "bad";

    public String format(final String input) {
        return prefix + input;
    }
}
