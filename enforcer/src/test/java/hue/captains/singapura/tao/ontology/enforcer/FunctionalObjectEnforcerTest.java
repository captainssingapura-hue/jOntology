package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadFunctionalObjectNonFinalField;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodFunctionalObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class FunctionalObjectEnforcerTest {

    private final FunctionalObjectEnforcer enforcer = new FunctionalObjectEnforcer();

    @Test
    void goodFunctionalObjectPassesEnforcement() {
        final List<ContractViolation> violations = enforcer.enforce(GoodFunctionalObject.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void nonFinalFieldIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadFunctionalObjectNonFinalField.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("not final")));
    }
}
