package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.ValueObject;

/**
 * Violates ValueObject: has no instance fields.
 */
public final class BadValueObjectNoFields implements ValueObject {

    @Override
    public boolean equals(final Object o) {
        return o instanceof BadValueObjectNoFields;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
