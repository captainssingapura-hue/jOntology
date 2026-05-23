package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.Immutable;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Enforces the Immutable contract:
 *  - All instance fields must be final.
 *  - No setter methods.
 *  - No static methods (FunctionalObject eliminates the need for them).
 *  - All instance field types must themselves be immutable (transitive check).
 */
public final class ImmutableEnforcer implements StatelessFunctionalObject {

    private static final Set<Class<?>> KNOWN_IMMUTABLE_TYPES = Set.of(
            String.class, Boolean.class, Byte.class, Short.class,
            Integer.class, Long.class, Float.class, Double.class,
            Character.class, BigDecimal.class, BigInteger.class,
            UUID.class, LocalDate.class, LocalTime.class,
            LocalDateTime.class, Instant.class, Duration.class,
            Period.class, URI.class, URL.class, Pattern.class,
            Class.class, Optional.class, Void.class
    );

    public List<ContractViolation> enforce(final Class<?> clazz) {
        return enforce(clazz, new HashSet<>());
    }

    List<ContractViolation> enforce(final Class<?> clazz, final Set<Class<?>> visited) {
        final List<ContractViolation> violations = new ArrayList<>();
        if (!visited.add(clazz)) {
            return violations;
        }

        for (final Field field : clazz.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (!Modifier.isFinal(field.getModifiers())) {
                violations.add(new ContractViolation(
                        clazz.getName() + ": instance field '" + field.getName() + "' is not final"));
            }
            if (!isImmutableType(field.getType(), visited)) {
                violations.add(new ContractViolation(
                        clazz.getName() + ": instance field '" + field.getName()
                                + "' has non-immutable type " + field.getType().getName()));
            }
        }

        for (final Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isStatic(method.getModifiers())) {
                if (isAllowedStaticMethod(method)) {
                    continue;
                }
                violations.add(new ContractViolation(
                        clazz.getName() + ": static method '" + method.getName()
                                + "' found — use a FunctionalObject instead"));
            }
            if (method.getName().startsWith("set") && method.getParameterCount() > 0) {
                violations.add(new ContractViolation(
                        clazz.getName() + ": setter method '" + method.getName() + "' found"));
            }
        }

        return violations;
    }

    private boolean isAllowedStaticMethod(final Method method) {
        // Synthetic methods (lambdas, bridge methods, etc.) are compiler-generated
        if (method.isSynthetic()) {
            return true;
        }
        // JVM entry point is the only acceptable static method
        if ("main".equals(method.getName())) {
            final Class<?>[] params = method.getParameterTypes();
            return params.length == 1 && params[0] == String[].class;
        }
        return false;
    }

    private boolean isImmutableType(final Class<?> type, final Set<Class<?>> visited) {
        if (type.isPrimitive()) {
            return true;
        }
        if (KNOWN_IMMUTABLE_TYPES.contains(type)) {
            return true;
        }
        if (type.isEnum()) {
            return true;
        }
        if (Immutable.class.isAssignableFrom(type)) {
            final List<ContractViolation> nested = enforce(type, new HashSet<>(visited));
            return nested.isEmpty();
        }
        return false;
    }

}
