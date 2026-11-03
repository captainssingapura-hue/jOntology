package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Vector;

/**
 * Violates Immutable: a Vector is trusted with its storage, not with every field.
 * A StringBuilder is not storage and is still refused.
 */
public final class BadVectorWithMutableField implements Vector<GoodValueObject> {

    private final GoodValueObject[] elements;
    private final StringBuilder label;

    public BadVectorWithMutableField(final GoodValueObject[] elements, final StringBuilder label) {
        this.elements = elements.clone();
        this.label = label;
    }

    @Override
    public boolean equals(final Object o) {
        return o instanceof BadVectorWithMutableField that
                && java.util.Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return java.util.Arrays.hashCode(elements);
    }
}
