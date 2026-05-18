package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.util.List;

/**
 * Enforces the StatelessFunctionalObject contract:
 *  - Must satisfy all Stateless rules (zero instance fields + Immutable rules).
 */
public final class StatelessFunctionalObjectEnforcer implements StatelessFunctionalObject {

    public List<ContractViolation> enforce(final Class<?> clazz) {
        return new StatelessEnforcer().enforce(clazz);
    }

}
