package hue.captains.singapura.tao.ontology;

/**
 * A value object that is a finite set of one {@link Scalar} type,
 *  with optional meta information the implementation makes part of its value:
 *  order, multiplicity (duplicates), or none for a plain set.
 *  Equal by its elements and its meta information. Vectors whose meta information
 *  differs, in kind or in value, are unequal.
 *  The elements and meta information are fixed at construction.
 *  How they are stored, iterated or looked up is the implementation's business.
 *
 * @param <S> the element type; a scalar, never a primitive or another vector
 */
public non-sealed interface Vector<S extends Scalar> extends ValueObject {
}
