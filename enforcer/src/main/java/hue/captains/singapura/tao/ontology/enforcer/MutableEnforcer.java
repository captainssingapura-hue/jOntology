package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.Immutable;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Enforces the Mutable contract:
 *  - A Mutable class must not also implement Immutable.
 */
public final class MutableEnforcer implements StatelessFunctionalObject {

    public List<ContractViolation> enforce(final Class<?> clazz) {
        final List<ContractViolation> violations = new ArrayList<>();
        if (Immutable.class.isAssignableFrom(clazz)) {
            violations.add(new ContractViolation(
                    clazz.getName() + ": implements both Mutable and Immutable"));
        }
        return violations;
    }

}
