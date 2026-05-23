package hue.captains.singapura.tao.ontology.utils;

import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;
import hue.captains.singapura.tao.ontology.enforcer.ContractViolation;
import hue.captains.singapura.tao.ontology.enforcer.OntologyEnforcer;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class EnforcingUtils implements StatelessFunctionalObject {

    public static void main(final String[] args) {
        final int exitCode = new EnforcingUtils().run(args);
        System.exit(exitCode);
    }

    public int run(final String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: EnforcingUtils <package-name>");
            return 1;
        }
        final String packageName = args[0];
        final PackageScanner scanner = new PackageScanner();
        final OntologyEnforcer enforcer = new OntologyEnforcer();
        try {
            final List<Class<?>> classes = scanner.scan(packageName);
            final Map<Class<?>, List<ContractViolation>> results = new LinkedHashMap<>();
            for (final Class<?> clazz : classes) {
                results.put(clazz, enforcer.enforce(clazz));
            }
            final EnforcingReport report = new EnforcingReport(packageName, results);
            System.out.print(report.format());
            return report.failingClasses() > 0 ? 1 : 0;
        } catch (final IOException e) {
            System.err.println("Failed to scan package '" + packageName + "': " + e.getMessage());
            return 2;
        }
    }
}
