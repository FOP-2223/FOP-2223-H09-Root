package h09;

import h09.basic.BasicBinaryOperationsTest;
import h09.basic.DoubleBasicBinaryOperationsTest;
import h09.basic.DoubleFactory;
import h09.basic.DoubleFactoryTest;
import h09.basic.IntegerBasicBinaryOperationsTest;
import h09.basic.IntegerFactory;
import h09.basic.IntegerFactoryTest;
import h09.basic.StringBasicBinaryOperationsTest;
import h09.basic.StringFactory;
import h09.basic.StringFactoryTest;
import h09.operator.H2_1_Test;
import org.sourcegrade.jagr.api.rubric.Criterion;
import org.sourcegrade.jagr.api.rubric.Grader;
import org.sourcegrade.jagr.api.rubric.JUnitTestRef;
import org.sourcegrade.jagr.api.rubric.Rubric;
import org.sourcegrade.jagr.api.rubric.RubricProvider;

public class H09_RubricProvider implements RubricProvider {

    public static final Criterion H1_1 = Criterion.builder()
        .shortDescription("H1.1 - IntegerFactory")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofClass(() -> IntegerFactoryTest.class))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_2 = Criterion.builder()
        .shortDescription("H1.2 - DoubleFactory")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofClass(() -> DoubleFactoryTest.class))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_3 = Criterion.builder()
        .shortDescription("H1.3 - StringFactory")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofClass(() -> StringFactoryTest.class))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_4_1 = Criterion.builder()
        .shortDescription("BasicBinaryOperations")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofClass(() -> BasicBinaryOperationsTest.class))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_4_2 = Criterion.builder()
        .shortDescription("DoubleBasicBinaryOperations + IntegerBasicBinaryOperations")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofClass(() -> DoubleBasicBinaryOperationsTest.class))
            .requirePass(JUnitTestRef.ofClass(() -> IntegerBasicBinaryOperationsTest.class))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_4_3 = Criterion.builder()
        .shortDescription("StringBasicBinaryOperations")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofClass(() -> StringBasicBinaryOperationsTest.class))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H1_4 = Criterion.builder()
        .shortDescription("H1.4 - BasicBinaryOperations")
        .addChildCriteria(H1_4_1, H1_4_2, H1_4_3)
        .build();

    public static final Criterion H1 = Criterion.builder()
        .shortDescription("H1 - BasicFactory")
        .addChildCriteria(H1_1, H1_2, H1_3, H1_4)
        .build();

    public static final Criterion H2_1 = Criterion.builder()
        .shortDescription("H2.1 - Erster Satz von binären Operatorklassen")
        .grader(Grader.testAwareBuilder()
            .requirePass(JUnitTestRef.ofClass(() -> H2_1_Test.class))
            .pointsPassedMax()
            .pointsFailedMin()
            .build())
        .build();

    public static final Criterion H2 = Criterion.builder()
        .shortDescription("H2 - Binary Operators")
        .addChildCriteria(H2_1)
        .build();

    public static final Rubric RUBRIC = Rubric.builder()
        .title("H09 - Ein Einblick in Generics")
        .addChildCriteria(H1, H2)
        .build();

    @Override
    public Rubric getRubric() {
        return RUBRIC;
    }
}
