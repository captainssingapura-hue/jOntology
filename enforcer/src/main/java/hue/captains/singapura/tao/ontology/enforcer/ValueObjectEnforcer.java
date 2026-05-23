package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * Enforces the ValueObject contract:
 *  - Must satisfy all Immutable rules (including transitive).
 *  - Must override equals(Object).
 *  - Must override hashCode().
 *  - Must have at least one instance field.
 */
public final class ValueObjectEnforcer implements StatelessFunctionalObject {

    public List<ContractViolation> enforce(final Class<?> clazz) {
        final List<ContractViolation> violations = new ArrayList<>();

        boolean hasInstanceField = false;
        for (final Field field : clazz.getDeclaredFields()) {
            if (!Modifier.isStatic(field.getModifiers())) {
                hasInstanceField = true;
                break;
            }
        }
        if (!hasInstanceField) {
            violations.add(new ContractViolation(
                    clazz.getName() + ": ValueObject must have at least one instance field"));
        }

        if (!overridesMethod(clazz, "equals", Object.class)) {
            violations.add(new ContractViolation(
                    clazz.getName() + ": ValueObject must override equals(Object)"));
        }

        if (!overridesMethod(clazz, "hashCode")) {
            violations.add(new ContractViolation(
                    clazz.getName() + ": ValueObject must override hashCode()"));
        }

        violations.addAll(new ImmutableEnforcer().enforce(clazz));
        return violations;
    }

    private boolean overridesMethod(final Class<?> clazz, final String name, final Class<?>... parameterTypes) {
        try {
            final Method method = clazz.getDeclaredMethod(name, parameterTypes);
            return !Modifier.isAbstract(method.getModifiers());
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

}
