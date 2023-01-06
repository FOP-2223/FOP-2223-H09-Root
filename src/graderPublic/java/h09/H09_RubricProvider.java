package h09;

import h09.basic.BasicBinaryOperationsTest;
import h09.basic.DoubleBasicBinaryOperationsTest;
import h09.basic.DoubleFactoryTest;
import h09.basic.IntegerBasicBinaryOperationsTest;
import h09.basic.IntegerFactoryTest;
import h09.basic.StringBasicBinaryOperationsTest;
import h09.basic.StringFactoryTest;
import h09.operator.ComposedBinaryOperatorTest;
import h09.operator.H2_1_Test;
import h09.operator.MaxOfTwoOperatorTest;
import h09.operator.SumWithCoefficientsOperatorTest;
import h09.sequence.ArraySequenceBasicTest;
import h09.sequence.BasicFactorySequenceBasicTest;
import h09.sequence.FibonacciSequenceBasicTest;
import h09.sequence.operation.FilteringSequenceBasicTest;
import h09.sequence.operation.FilteringSequenceIntermediateTest;
import h09.sequence.operation.FlatteningTransformingSequenceBasicTest;
import h09.sequence.operation.FlatteningTransformingSequenceIntermediateTest;
import h09.sequence.operation.TransformingSequenceBasicTest;
import h09.sequence.operation.TransformingSequenceIntermediateTest;
import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

import java.util.concurrent.Callable;

public class H09_RubricProvider implements RubricProvider {

    public static final Criterion H1_1 = createCriterion("H1.1 - IntegerFactory", () -> IntegerFactoryTest.class);
    public static final Criterion H1_2 = createCriterion("H1.2 - DoubleFactory", () -> DoubleFactoryTest.class);
    public static final Criterion H1_3 = createCriterion("H1.3 - StringFactory", () -> StringFactoryTest.class);

    public static final Criterion H1_4_1 = createCriterion("BasicBinaryOperationsTest", () -> BasicBinaryOperationsTest.class);

    public static final Criterion H1_4_2 = Criterion.builder()
        .shortDescription("DoubleBasicBinaryOperations + IntegerBasicBinaryOperations")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofClass(() -> DoubleBasicBinaryOperationsTest.class))
            .requirePass(JUnitTestRef.ofClass(() -> IntegerBasicBinaryOperationsTest.class))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_4_3 = createCriterion("StringBasicBinaryOperations", () -> StringBasicBinaryOperationsTest.class);

    public static final Criterion H1_4 = Criterion.builder()
        .shortDescription("H1.4 - BasicBinaryOperations")
        .addChildCriteria(H1_4_1, H1_4_2, H1_4_3)
        .build();

    public static final Criterion H1 = Criterion.builder()
        .shortDescription("H1 - BasicFactory")
        .addChildCriteria(H1_1, H1_2, H1_3, H1_4)
        .build();

    public static final Criterion H2_1 = createCriterion("H2.1 - Erster Satz von binären Operatorklassen", () -> H2_1_Test.class);
    public static final Criterion H2_2 = createCriterion("H2.2 - ComposedBinaryOperator", () -> ComposedBinaryOperatorTest.class);
    public static final Criterion H2_3 = createCriterion("H2.3 - MaxOfTwoOperator", () -> MaxOfTwoOperatorTest.class);
    public static final Criterion H2_4 = createCriterion("H2.4 - SumWithCoefficientsTest", () -> SumWithCoefficientsOperatorTest.class);

    public static final Criterion H2 = Criterion.builder()
        .shortDescription("H2 - Binary Operators")
        .addChildCriteria(H2_1, H2_2, H2_3, H2_4)
        .build();

    public static final Criterion H3_1_1 = createCriterion(
        "Klassensignature von ArraySequence ist korrekt und der Iterator funktioniert für einfache Fälle",
        () -> ArraySequenceBasicTest.class);

    public static final Criterion H3_1_2 = Criterion.builder()
        .shortDescription("ArraySequence is vollständig korrekt implementiert")
        .build();

    public static final Criterion H3_1 = Criterion.builder()
        .shortDescription("H3.1 - ArraySequence")
        .addChildCriteria(H3_1_1, H3_1_2)
        .build();

    public static final Criterion H3_2_1 = createCriterion(
        "Klassensignature von FibonacciSequence ist korrekt und der Iterator funktioniert für einfache Fälle",
        () -> FibonacciSequenceBasicTest.class);

    public static final Criterion H3_2_2 = Criterion.builder()
        .shortDescription("FibonacciSequence is vollständig korrekt implementiert")
        .build();

    public static final Criterion H3_2 = Criterion.builder()
        .shortDescription("H3.2 - FibonacciSequence")
        .addChildCriteria(H3_2_1, H3_2_2)
        .build();

    public static final Criterion H3_3_1 = createCriterion(
        "Klassensignatur von BasicFactorySequence ist korrekt",
        () -> BasicFactorySequenceBasicTest.class);

    public static final Criterion H3_3_2 = Criterion.builder()
        .shortDescription("BasicFactorySequence is vollständig korrekt implementiert")
        .build();

    public static final Criterion H3_3 = Criterion.builder()
        .shortDescription("H3.3 - BasicFactorySequence")
        .addChildCriteria(H3_3_1, H3_3_2)
        .build();

    public static final Criterion H3 = Criterion.builder()
        .shortDescription("H3 - Sequences")
        .addChildCriteria(H3_1, H3_2, H3_3)
        .build();

    public static final Criterion H4_1_1 = createCriterion(
        "Klassensignatur von FilteringSequence ist korrekt und der Iterator funktioniert für einfache Fälle",
        () -> FilteringSequenceBasicTest.class);

    public static final Criterion H4_1_2 = createCriterion(
        "Attribute und konstruktor sind bis auf Wildcards korrekt implementiert",
        () -> FilteringSequenceIntermediateTest.class);

    public static final Criterion H4_1_3 = Criterion.builder()
        .shortDescription("FilteringSequence is vollständig korrekt implementiert")
        .build();

    public static final Criterion H4_1 = Criterion.builder()
        .shortDescription("H4.1 - FilteringSequence")
        .addChildCriteria(H4_1_1, H4_1_2, H4_1_3)
        .build();

    public static final Criterion H4_2_1 = createCriterion(
        "Klassensignatur von TransformingSequence ist korrekt und der Iterator funktioniert für einfache Fälle",
        () -> TransformingSequenceBasicTest.class);

    public static final Criterion H4_2_2 = createCriterion(
        "Attribute und konstruktor von TransformingSequence sind bis auf Wildcards korrekt implementiert",
        () -> TransformingSequenceIntermediateTest.class);

    public static final Criterion H4_2_3 = Criterion.builder()
        .shortDescription("TransformingSequence is vollständig korrekt implementiert")
        .build();

    public static final Criterion H4_2 = Criterion.builder()
        .shortDescription("H4.2 - TransformingSequence")
        .addChildCriteria(H4_2_1, H4_2_2, H4_2_3)
        .build();

    public static final Criterion H4_3_1 = createCriterion(
        "Klassensignatur von FlatteningTransformingSequence ist korrekt und der Iterator funktioniert für einfache Fälle",
        () -> FlatteningTransformingSequenceBasicTest.class);

    public static final Criterion H4_3_2 = createCriterion(
        "Attribute und konstruktor von FlatteningTransformingSequence sind bis auf Wildcards korrekt implementiert",
        () -> FlatteningTransformingSequenceIntermediateTest.class);

    public static final Criterion H4_3_3 = Criterion.builder()
        .shortDescription("Generics von FlatteningTransformingSequence sind vollständig korrekt implementiert")
        .build();

    public static final Criterion H4_3_4 = Criterion.builder()
        .shortDescription("FlatteningTransformingSequence is vollständig korrekt implementiert")
        .build();

    public static final Criterion H4_3 = Criterion.builder()
        .shortDescription("H4.3 - FlatteningTransformingSequence")
        .addChildCriteria(H4_3_1, H4_3_2, H4_3_3, H4_3_4)
        .build();

    public static final Criterion H4_4 = Criterion.builder()
        .shortDescription("H4.4 - Einfachere Syntax bei Verwendung von Sequences")
        .maxPoints(3)
        .build();

    public static final Criterion H4 = Criterion.builder()
        .shortDescription("H4 - Sequence Operationen")
        .addChildCriteria(H4_1, H4_2, H4_3, H4_4)
        .build();

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H09 - Ein Einblick in Generics")
        .addChildCriteria(H1, H2, H3, H4)
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }

    private static Criterion createCriterion(String shortDescription, Callable<Class<?>> testClassSupplier) {
        return Criterion.builder()
            .shortDescription(shortDescription)
            .grader(Grader.testAwareBuilder()
                .requirePass(JUnitTestRef.ofClass(testClassSupplier))
                .pointsPassedMax()
                .pointsFailedMin()
                .build())
            .build();
    }
}
