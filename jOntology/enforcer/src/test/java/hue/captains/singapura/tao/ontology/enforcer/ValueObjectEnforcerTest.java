package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadValueObjectNoEquals;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadValueObjectNoFields;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadValueObjectStaticFieldsOnly;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodValueObject;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodValueObjectWithStaticField;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class ValueObjectEnforcerTest {

    private final ValueObjectEnforcer enforcer = new ValueObjectEnforcer();

    @Test
    void goodValueObjectPassesEnforcement() {
        final List<ContractViolation> violations = enforcer.enforce(GoodValueObject.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void missingEqualsAndHashCodeIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadValueObjectNoEquals.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("equals")));
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("hashCode")));
    }

    @Test
    void noInstanceFieldsIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadValueObjectNoFields.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("at least one instance field")));
    }

    @Test
    void staticFieldsOnlyIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadValueObjectStaticFieldsOnly.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("at least one instance field")));
    }

    @Test
    void valueObjectWithStaticAndInstanceFieldsPasses() {
        final List<ContractViolation> violations = enforcer.enforce(GoodValueObjectWithStaticField.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void contractViolationRecordPassesValueObjectCheck() {
        final List<ContractViolation> violations = enforcer.enforce(ContractViolation.class);
        assertTrue(violations.isEmpty(), () -> "ContractViolation violates ValueObject rules: " + violations);
    }
}
