package hue.captains.singapura.tao.ontology.enforcer.fixtures;

import hue.captains.singapura.tao.ontology.Immutable;
import hue.captains.singapura.tao.ontology.Mutable;

/**
 * Violates Mutable: also implements Immutable.
 */
public final class BadMutableAlsoImmutable implements Mutable, Immutable {
}
