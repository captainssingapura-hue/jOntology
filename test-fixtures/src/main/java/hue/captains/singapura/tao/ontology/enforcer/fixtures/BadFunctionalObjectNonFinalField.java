package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.FunctionalObject;

/**
 * Violates FunctionalObject: has a non-final field (breaks Immutable contract).
 */
public final class BadFunctionalObjectNonFinalField implements FunctionalObject {

    private String prefix;

    public BadFunctionalObjectNonFinalField(final String prefix) {
        this.prefix = prefix;
    }

    public String format(final String input) {
        return prefix + input;
    }
}
