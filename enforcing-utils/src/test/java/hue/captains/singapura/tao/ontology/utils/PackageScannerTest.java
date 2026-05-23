package hue.captains.singapura.tao.ontology.utils;

import hue.captains.singapura.tao.ontology.Immutable;
import hue.captains.singapura.tao.ontology.Mutable;
import hue.captains.singapura.tao.ontology.utils.fixtures.ScanFixtures;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PackageScannerTest {

    private final PackageScanner scanner = new PackageScanner();

    @Test
    void scanFindsTopLevelClassesInPackage() throws IOException {
        final List<Class<?>> classes = scanner.scan("hue.captains.singapura.tao.ontology");
        assertTrue(classes.contains(Immutable.class));
        assertTrue(classes.contains(Mutable.class));
    }

    @Test
    void scanFindsNestedClasses() throws IOException {
        final List<Class<?>> classes = scanner.scan("hue.captains.singapura.tao.ontology.utils.fixtures");
        assertTrue(classes.contains(ScanFixtures.Nested.class));
        assertTrue(classes.contains(ScanFixtures.Nested.DeepNested.class));
    }

    @Test
    void scanReturnsEmptyListForUnknownPackage() throws IOException {
        final List<Class<?>> classes = scanner.scan("hue.captains.singapura.tao.ontology.nonexistent");
        assertTrue(classes.isEmpty());
    }
}
