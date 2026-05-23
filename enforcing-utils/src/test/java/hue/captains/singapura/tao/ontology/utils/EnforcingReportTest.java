package hue.captains.singapura.tao.ontology.utils;

import hue.captains.singapura.tao.ontology.enforcer.ContractViolation;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EnforcingReportTest {

    @Test
    void totalClassesReflectsResultSize() {
        final EnforcingReport report = new EnforcingReport("com.example", Map.of(
                String.class, List.of(),
                Integer.class, List.of(new ContractViolation("field not final"))
        ));
        assertEquals(2, report.totalClasses());
    }

    @Test
    void passingAndFailingClassesSumToTotal() {
        final EnforcingReport report = new EnforcingReport("com.example", Map.of(
                String.class, List.of(),
                Integer.class, List.of(new ContractViolation("field not final")),
                Long.class, List.of()
        ));
        assertEquals(1, report.failingClasses());
        assertEquals(2, report.passingClasses());
        assertEquals(report.totalClasses(), report.passingClasses() + report.failingClasses());
    }

    @Test
    void formatContainsPackageName() {
        final EnforcingReport report = new EnforcingReport("com.example.mypackage", Map.of());
        assertTrue(report.format().contains("com.example.mypackage"));
    }

    @Test
    void formatShowsTickForPassingClass() {
        final EnforcingReport report = new EnforcingReport("com.example", Map.of(
                String.class, List.of()
        ));
        assertTrue(report.format().contains("✓"));
    }

    @Test
    void formatShowsCrossAndViolationForFailingClass() {
        final EnforcingReport report = new EnforcingReport("com.example", Map.of(
                Integer.class, List.of(new ContractViolation("field not final"))
        ));
        final String formatted = report.format();
        assertTrue(formatted.contains("✗"));
        assertTrue(formatted.contains("field not final"));
    }

    @Test
    void formatShowsSummaryLine() {
        final EnforcingReport report = new EnforcingReport("com.example", Map.of(
                String.class, List.of(),
                Integer.class, List.of(new ContractViolation("field not final"))
        ));
        assertTrue(report.format().contains("Summary: 1 passed, 1 failed"));
    }

    @Test
    void emptyReportShowsZeroClasses() {
        final EnforcingReport report = new EnforcingReport("com.empty", Map.of());
        assertTrue(report.format().contains("Scanned : 0 class(es)"));
        assertEquals(0, report.totalClasses());
        assertEquals(0, report.failingClasses());
        assertEquals(0, report.passingClasses());
    }
}
