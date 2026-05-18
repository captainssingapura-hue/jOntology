package hue.captains.singapura.tao.ontology.enforcer;

import hue.captains.singapura.tao.ontology.FunctionalObject;
import hue.captains.singapura.tao.ontology.Immutable;
import hue.captains.singapura.tao.ontology.Mutable;
import hue.captains.singapura.tao.ontology.Stateless;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;
import hue.captains.singapura.tao.ontology.ValueObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Main entry point that detects which ontology interfaces a class implements
 * and runs all applicable contract checks.
 */
public final class OntologyEnforcer implements StatelessFunctionalObject {

    /**
     * The JVM requires a static main entry point.
     * This is the only acceptable static method — it immediately delegates to an instance.
     */
    public static void main(final String[] args) throws ClassNotFoundException {
        System.exit(new OntologyEnforcer().run(args));
    }

    public List<ContractViolation> enforce(final Class<?> clazz) {
        final List<ContractViolation> violations = new ArrayList<>();

        boolean hasOntologyMarker = false;

        if (Mutable.class.isAssignableFrom(clazz)) {
            hasOntologyMarker = true;
            violations.addAll(new MutableEnforcer().enforce(clazz));
        }

        if (StatelessFunctionalObject.class.isAssignableFrom(clazz)) {
            hasOntologyMarker = true;
            violations.addAll(new StatelessFunctionalObjectEnforcer().enforce(clazz));
        } else if (FunctionalObject.class.isAssignableFrom(clazz)) {
            hasOntologyMarker = true;
            violations.addAll(new FunctionalObjectEnforcer().enforce(clazz));
        } else if (Stateless.class.isAssignableFrom(clazz)) {
            hasOntologyMarker = true;
            violations.addAll(new StatelessEnforcer().enforce(clazz));
        } else if (ValueObject.class.isAssignableFrom(clazz)) {
            hasOntologyMarker = true;
            violations.addAll(new ValueObjectEnforcer().enforce(clazz));
        } else if (Immutable.class.isAssignableFrom(clazz)) {
            hasOntologyMarker = true;
            violations.addAll(new ImmutableEnforcer().enforce(clazz));
        }

        if (!hasOntologyMarker) {
            violations.add(new ContractViolation(
                    clazz.getName() + ": Who are you, where do you come from, and where are you going?"));
        }

        return violations;
    }

    int run(final String[] args) throws ClassNotFoundException {
        if (args.length == 0) {
            System.err.println("Usage: OntologyEnforcer <fully-qualified-class-name> [...]");
            return 1;
        }

        boolean hasViolations = false;
        for (final String className : args) {
            final Class<?> clazz = Class.forName(className);
            final List<ContractViolation> violations = enforce(clazz);
            if (!violations.isEmpty()) {
                hasViolations = true;
                violations.forEach(v -> System.err.println("VIOLATION: " + v.message()));
            } else {
                System.out.println("OK: " + className);
            }
        }

        return hasViolations ? 1 : 0;
    }
}
