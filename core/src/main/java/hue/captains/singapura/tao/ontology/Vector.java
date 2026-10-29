package hue.captains.singapura.tao.ontology;

/**
 * A value object that is a finite sequence of one {@link Scalar} type.
 *  Equal by its elements, in order. The elements are fixed at construction.
 *  How they are stored, iterated or looked up is the implementation's business.
 *
 * @param <S> the element type; a scalar, never a primitive or another vector
 */
public non-sealed interface Vector<S extends Scalar> extends ValueObject {
}
