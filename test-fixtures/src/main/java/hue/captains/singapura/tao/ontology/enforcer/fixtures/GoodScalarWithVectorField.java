package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Scalar;
import hue.captains.singapura.tao.ontology.Vector;

/**
 * A Scalar holding a Vector by its interface and another by a concrete implementation.
 * Both pass: the implementation's storage is trusted transitively.
 */
public record GoodScalarWithVectorField(
        Vector<GoodValueObject> byInterface,
        GoodVectorArrayStorage byImplementation
) implements Scalar {
}
