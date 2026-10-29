package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Vector;

import java.util.List;

/**
 * A well-behaved Vector that stores its elements in a List, copied on construction.
 * The List is the implementer's storage and is trusted by the enforcer.
 */
public record GoodVectorListStorage(List<GoodValueObject> elements) implements Vector<GoodValueObject> {

    public GoodVectorListStorage {
        elements = List.copyOf(elements);
    }
}
