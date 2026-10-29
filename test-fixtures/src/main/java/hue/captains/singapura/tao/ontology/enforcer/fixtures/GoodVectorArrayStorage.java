package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Vector;

import java.util.Arrays;

/**
 * A well-behaved Vector that stores its elements in an array.
 * The array is the implementer's storage and is trusted by the enforcer.
 */
public final class GoodVectorArrayStorage implements Vector<GoodValueObject> {

    private final GoodValueObject[] elements;

    public GoodVectorArrayStorage(final GoodValueObject[] elements) {
        this.elements = elements.clone();
    }

    public int size() {
        return elements.length;
    }

    public GoodValueObject get(final int i) {
        return elements[i];
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof GoodVectorArrayStorage that)) return false;
        return Arrays.equals(elements, that.elements);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }
}
