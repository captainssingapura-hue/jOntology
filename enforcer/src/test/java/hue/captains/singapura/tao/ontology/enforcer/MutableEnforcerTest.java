package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadMutableAlsoImmutable;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodMutable;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class MutableEnforcerTest {

    private final MutableEnforcer enforcer = new MutableEnforcer();

    @Test
    void goodMutablePassesEnforcement() {
        final List<ContractViolation> violations = enforcer.enforce(GoodMutable.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void mutableAlsoImmutableIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadMutableAlsoImmutable.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("both Mutable and Immutable")));
    }
}
