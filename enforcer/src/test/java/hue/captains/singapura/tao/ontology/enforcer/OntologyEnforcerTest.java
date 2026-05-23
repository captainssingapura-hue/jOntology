package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadImmutableNonFinalField;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.BadMutableAlsoImmutable;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodFunctionalObject;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodImmutable;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodMutable;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodStateless;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodStatelessFunctionalObject;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodValueObject;
import hue.captains.singapura.tao.ontology.enforcer.fixtures.UnmarkedClass;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class OntologyEnforcerTest {

    private final OntologyEnforcer enforcer = new OntologyEnforcer();

    @Test
    void goodImmutablePasses() {
        final List<ContractViolation> violations = enforcer.enforce(GoodImmutable.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void goodMutablePasses() {
        final List<ContractViolation> violations = enforcer.enforce(GoodMutable.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void goodStatelessPasses() {
        final List<ContractViolation> violations = enforcer.enforce(GoodStateless.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void goodValueObjectPasses() {
        final List<ContractViolation> violations = enforcer.enforce(GoodValueObject.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void goodFunctionalObjectPasses() {
        final List<ContractViolation> violations = enforcer.enforce(GoodFunctionalObject.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void goodStatelessFunctionalObjectPasses() {
        final List<ContractViolation> violations = enforcer.enforce(GoodStatelessFunctionalObject.class);
        assertTrue(violations.isEmpty(), () -> "Expected no violations but got: " + violations);
    }

    @Test
    void badImmutableIsDetected() {
        final List<ContractViolation> violations = enforcer.enforce(BadImmutableNonFinalField.class);
        assertFalse(violations.isEmpty());
    }

    @Test
    void badMutableIsDetected() {
        final List<ContractViolation> violations = enforcer.enforce(BadMutableAlsoImmutable.class);
        assertFalse(violations.isEmpty());
    }

    @Test
    void unmarkedClassIsViolation() {
        final List<ContractViolation> violations = enforcer.enforce(UnmarkedClass.class);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.message().contains("Who are you")));
    }

    @Test
    void runWithNoArgsReturnsOne() throws ClassNotFoundException {
        final int exitCode = enforcer.run(new String[]{});
        assertEquals(1, exitCode);
    }

    @Test
    void runWithValidClassReturnsZero() throws ClassNotFoundException {
        final int exitCode = enforcer.run(new String[]{
                "hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodImmutable"
        });
        assertEquals(0, exitCode);
    }

    @Test
    void runWithViolatingClassReturnsOne() throws ClassNotFoundException {
        final int exitCode = enforcer.run(new String[]{
                "hue.captains.singapura.tao.ontology.enforcer.fixtures.BadImmutableNonFinalField"
        });
        assertEquals(1, exitCode);
    }

    @Test
    void runWithMultipleClassesReportsAll() throws ClassNotFoundException {
        final int exitCode = enforcer.run(new String[]{
                "hue.captains.singapura.tao.ontology.enforcer.fixtures.GoodImmutable",
                "hue.captains.singapura.tao.ontology.enforcer.fixtures.BadImmutableNonFinalField"
        });
        assertEquals(1, exitCode);
    }

    @Test
    void runWithUnknownClassThrows() {
        assertThrows(ClassNotFoundException.class, () ->
                enforcer.run(new String[]{"com.does.not.Exist"}));
    }
}
