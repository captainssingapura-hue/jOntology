package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadStatelessWithField;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodStateless;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class StatelessEnforcerTest {

    private final StatelessEnforcer enforcer = new StatelessEnforcer();

    @Test
    void goodStatelessPassesEnforcement() {
        final List<ContractViolation> violations = enforcer.enforce(GoodStateless.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void statelessWithFieldIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadStatelessWithField.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("instance field")));
    }
}
