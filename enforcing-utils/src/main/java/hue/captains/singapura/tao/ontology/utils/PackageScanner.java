package hue.captains.singapura.tao.ontology.utils;

import com.google.common.reflect.ClassPath;
import hue.captains.singapura.tao.ontology.StatelessFunctionalObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class PackageScanner implements StatelessFunctionalObject {

    public List<Class<?>> scan(final String packageName) throws IOException {
        final ClassPath classPath = ClassPath.from(Thread.currentThread().getContextClassLoader());
        final List<Class<?>> result = new ArrayList<>();
        for (final ClassPath.ClassInfo info : classPath.getTopLevelClassesRecursive(packageName)) {
            final Class<?> clazz = info.load();
            result.add(clazz);
            collectNested(clazz, result);
        }
        return List.copyOf(result);
    }

    private void collectNested(final Class<?> clazz, final List<Class<?>> result) {
        for (final Class<?> nested : clazz.getDeclaredClasses()) {
            result.add(nested);
            collectNested(nested, result);
        }
    }
}
