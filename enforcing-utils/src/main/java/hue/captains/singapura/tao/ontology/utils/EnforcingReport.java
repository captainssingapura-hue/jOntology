package hue.captains.singapura.tao.ontology.utils;

import hue.captains.singapura.tao.ontology.ValueObject;
import hue.captains.singapura.tao.ontology.enforcer.ContractViolation;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public record EnforcingReport(
        String packageName,
        Map<Class<?>, List<ContractViolation>> results
) implements ValueObject {

    public EnforcingReport {
        results = Map.copyOf(results);
    }

    public int totalClasses() {
        return results.size();
    }

    public int failingClasses() {
        return (int) results.values().stream().filter(v -> !v.isEmpty()).count();
    }

    public int passingClasses() {
        return totalClasses() - failingClasses();
    }

    public String format() {
        final String line = "=".repeat(60);
        final StringBuilder sb = new StringBuilder();
        sb.append(line).append("\n");
        sb.append("jOntology Enforcement Report\n");
        sb.append("Package : ").append(packageName).append("\n");
        sb.append("Scanned : ").append(totalClasses()).append(" class(es)\n");
        sb.append(line).append("\n\n");

        final List<Map.Entry<Class<?>, List<ContractViolation>>> sorted = results.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Class::getName)))
                .toList();

        for (final Map.Entry<Class<?>, List<ContractViolation>> entry : sorted) {
            final List<ContractViolation> violations = entry.getValue();
            if (violations.isEmpty()) {
                sb.append("✓ ").append(entry.getKey().getName()).append("\n");
            } else {
                sb.append("✗ ").append(entry.getKey().getName()).append("\n");
                for (final ContractViolation v : violations) {
                    sb.append("    - ").append(v.message()).append("\n");
                }
            }
        }

        sb.append("\n").append(line).append("\n");
        sb.append("Summary: ").append(passingClasses()).append(" passed, ")
                .append(failingClasses()).append(" failed\n");
        sb.append(line).append("\n");
        return sb.toString();
    }
}
