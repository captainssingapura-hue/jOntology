package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Enforces the Stateless contract:
 *  - Must have zero instance fields.
 *  - Must satisfy all Immutable rules.
 */
public final class StatelessEnforcer implements StatelessFunctionalObject {

    public List<ContractViolation> enforce(final Class<?> clazz) {
        final List<ContractViolation> violations = new ArrayList<>();

        for (final Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                violations.add(new ContractViolation(
                        clazz.getName() + ": Stateless class has instance field '" + field.getName() + "'"));
            }
        }

        violations.addAll(new ImmutableEnforcer().enforce(clazz));
        return violations;
    }

}
