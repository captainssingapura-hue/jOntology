package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * Enforces the FunctionalObject contract:
 *  - Must satisfy all Immutable rules (including transitive).
 */
public final class FunctionalObjectEnforcer implements StatelessFunctionalObject {

    public List<ContractViolation> enforce(final Class<?> clazz) {
        return new ImmutableEnforcer().enforce(clazz);
    }

}
