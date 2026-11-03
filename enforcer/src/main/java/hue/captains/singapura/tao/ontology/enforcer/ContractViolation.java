package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.Scalar;

/**
 * A violation found when enforcing an ontology contract against a class.
 */
public record ContractViolation(String message) implements Scalar {
}
