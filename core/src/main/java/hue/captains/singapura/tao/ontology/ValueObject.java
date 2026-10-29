package hue.captains.singapura.tao.ontology;

/**
 * A value object's identity is equivalent to the values it holds.
 *  Typically, it needs to hold immutable state(values) which determine its identity.
 *
 * A value object is either a {@link Scalar} or a {@link Vector}; nothing else.
 */
public sealed interface ValueObject extends Immutable permits Scalar, Vector {
}
