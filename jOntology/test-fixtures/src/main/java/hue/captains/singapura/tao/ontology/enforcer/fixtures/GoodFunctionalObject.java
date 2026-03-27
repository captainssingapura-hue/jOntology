package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.FunctionalObject;

/**
 * A well-behaved FunctionalObject: immutable config + instance methods.
 */
public final class GoodFunctionalObject implements FunctionalObject {

    private final String prefix;

    public GoodFunctionalObject(final String prefix) {
        this.prefix = prefix;
    }

    public String format(final String input) {
        return prefix + input;
    }
}
