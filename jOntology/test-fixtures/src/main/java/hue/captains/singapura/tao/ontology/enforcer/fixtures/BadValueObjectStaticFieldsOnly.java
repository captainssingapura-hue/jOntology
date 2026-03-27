package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.Objects;

/**
 * Violates ValueObject: has only static fields, no instance fields.
 * Tests the branch where the field loop iterates but only finds static fields.
 */
public final class BadValueObjectStaticFieldsOnly implements ValueObject {

    private static final String DEFAULT_NAME = "default";

    @Override
    public boolean equals(final Object o) {
        return o instanceof BadValueObjectStaticFieldsOnly;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
