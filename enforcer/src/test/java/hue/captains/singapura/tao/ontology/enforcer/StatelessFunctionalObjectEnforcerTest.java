package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadStatelessFunctionalObjectWithField;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodStatelessFunctionalObject;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class StatelessFunctionalObjectEnforcerTest {

    private final StatelessFunctionalObjectEnforcer enforcer = new StatelessFunctionalObjectEnforcer();

    @Test
    void goodStatelessFunctionalObjectPassesEnforcement() {
        final List<ContractViolation> violations = enforcer.enforce(GoodStatelessFunctionalObject.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void statelessFunctionalObjectWithFieldIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(BadStatelessFunctionalObjectWithField.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("instance field")));
    }

    @Test
    void allEnforcersPassTheirOwnCheck() {
        final List<ContractViolation> allViolations = List.of(
                ImmutableEnforcer.class,
                MutableEnforcer.class,
                StatelessEnforcer.class,
                ValueObjectEnforcer.class,
                FunctionalObjectEnforcer.class,
                StatelessFunctionalObjectEnforcer.class,
                OntologyEnforcer.class
        ).stream()
                .flatMap(c -> enforcer.enforce(c).stream())
                .toList();

        assertTrue(allViolations.isEmpty(),
                () -> "Enforcer classes violate their own rules: " + allViolations);
    }
}
