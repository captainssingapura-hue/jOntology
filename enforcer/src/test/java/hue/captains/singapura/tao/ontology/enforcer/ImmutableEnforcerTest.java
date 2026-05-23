package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadImmutableNonFinalField;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadImmutableWithMutableField;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadImmutableWithSetter;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadImmutableWithStaticMethod;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadImmutableTransitive;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodImmutable;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodImmutableTransitive;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodImmutableWithEnum;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ImmutableEnforcerTest {

    private final ImmutableEnforcer enforcer = new ImmutableEnforcer();

    @Test
    void goodImmutablePassesEnforcement() {
        final List<ContractViolation> violations = enforcer.enforce(GoodImmutable.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void transitiveImmutablePassesEnforcement() {
        final List<ContractViolation> violations = enforcer.enforce(GoodImmutableTransitive.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void nonFinalFieldIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadImmutableNonFinalField.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("not final")));
    }

    @Test
    void setterMethodIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadImmutableWithSetter.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("setter method")));
    }

    @Test
    void mutableFieldTypeIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadImmutableWithMutableField.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("non-immutable type")));
    }

    @Test
    void transitivelyBadImmutableIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadImmutableTransitive.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("non-immutable type")));
    }

    @Test
    void staticMethodIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadImmutableWithStaticMethod.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("static method")));
    }

    @Test
    void enumFieldTypeIsImmutable() {
        final List<ContractViolation> violations = enforcer.enforce(GoodImmutableWithEnum.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void enforcerPassesItsOwnCheck() {
        final List<ContractViolation> violations = enforcer.enforce(ImmutableEnforcer.class);
        assertTrue(violations.isEmpty(), () -> "Enforcer violates its own rules: " + violations);
    }
}
