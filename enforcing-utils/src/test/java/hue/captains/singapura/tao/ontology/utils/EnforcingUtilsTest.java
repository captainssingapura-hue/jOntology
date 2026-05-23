package hue.captains.singapura.tao.ontology.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnforcingUtilsTest {

    private final EnforcingUtils utils = new EnforcingUtils();

    @Test
    void runWithNoArgsReturnsOne() {
        final int exitCode = utils.run(new String[]{});
        assertEquals(1, exitCode);
    }

    @Test
    void runWithValidPackageReturnsZeroOrOne() throws Exception {
        final int exitCode = utils.run(new String[]{"hue.captains.singapura.tao.ontology"});
        assertTrue(exitCode == 0 || exitCode == 1);
    }

    @Test
    void runWithUnknownPackageReturnsZero() {
        final int exitCode = utils.run(new String[]{"hue.captains.singapura.tao.ontology.nonexistent"});
        assertEquals(0, exitCode);
    }
}
