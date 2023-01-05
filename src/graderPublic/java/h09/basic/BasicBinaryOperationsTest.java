package h09.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sourcegrade.jagr.api.rubric.TestForSubmission;
import org.sourcegrade.jagr.api.testing.SourceFile;
import org.sourcegrade.jagr.api.testing.TestCycle;
import org.sourcegrade.jagr.api.testing.extension.JagrExecutionCondition;
import org.sourcegrade.jagr.api.testing.extension.TestCycleResolver;
import spoon.Launcher;
import spoon.reflect.declaration.CtInterface;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeParameterReference;
import spoon.support.compiler.VirtualFile;

import java.util.Objects;

@TestForSubmission
@ExtendWith(JagrExecutionCondition.class)
public final class BasicBinaryOperationsTest {

    @Test
    void testClassSignature() {
        Assertions.assertArrayEquals(new String[]{"X", "Y"}, getInterface().getFormalCtTypeParameters().stream()
                .map(CtType::getSimpleName)
                .toArray(String[]::new),
            "BasicBinaryOperations should have type parameters X and Y");
    }

    @Test
    void testAddSignature() {
        final CtInterface<?> iface = getInterface();
        final CtMethod<?> add = iface.getMethodsByName("add").get(0);
        final CtTypeParameterReference xType = iface.getFormalCtTypeParameters().get(0).getReference();
        Assertions.assertEquals(2, add.getParameters().size(),
            "add should have two parameters");
        Assertions.assertEquals(xType, add.getParameters().get(0).getType(),
            "add's first parameter should be of type X");
        Assertions.assertEquals(xType, add.getParameters().get(1).getType(),
            "add's second parameter should be of type X");
        Assertions.assertEquals(xType, add.getType(),
            "add's return type should be X");
    }

    @Test
    void testMulSignature() {
        final CtInterface<?> iface = getInterface();
        final CtMethod<?> mul = iface.getMethodsByName("mul").get(0);
        final CtTypeParameterReference xType = iface.getFormalCtTypeParameters().get(0).getReference();
        final CtTypeParameterReference yType = iface.getFormalCtTypeParameters().get(1).getReference();
        Assertions.assertEquals(2, mul.getParameters().size(),
            "mul should have two parameters");
        Assertions.assertEquals(xType, mul.getParameters().get(0).getType(),
            "mul's first parameter should be of type X");
        Assertions.assertEquals(yType, mul.getParameters().get(1).getType(),
            "mul's second parameter should be of type Y");
        Assertions.assertEquals(xType, mul.getType(),
            "mul's return type should be X");
    }

    @SuppressWarnings("UnstableApiUsage")
    private static CtInterface<?> getInterface() {
        final TestCycle testCycle = Objects.requireNonNull(TestCycleResolver.getTestCycle(), "TestCycle not found");
        final SourceFile sourceFile = testCycle.getSubmission().getSourceFile("h09/basic/BasicBinaryOperations.java");
        Assertions.assertNotNull(sourceFile, "Could not find source file h09/basic/BasicBinaryOperations.java");
        final Launcher launcher = new Launcher();
        launcher.addInputResource(new VirtualFile(sourceFile.getContent()));
        return (CtInterface<?>) launcher.buildModel().getAllTypes().stream().findFirst().orElseThrow(
            () -> new IllegalStateException("Error building model for h09/basic/BasicBinaryOperations.java"));
    }
}
