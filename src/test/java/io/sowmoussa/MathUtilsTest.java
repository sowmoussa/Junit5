package io.sowmoussa;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("When running MathUtils.class")
class MathUtilsTest {

    MathUtils mathUtils;

    @BeforeAll
    void init() {
        System.out.println("Before all..");
    }

    @BeforeEach
    void setUp() {
        mathUtils = new MathUtils();
    }

    @AfterEach
    void cleanup() {
        System.out.println("Cleaning up...");
    }

    @Nested
    @DisplayName("Testing add method")
    @Tag("Math")
    class AddTest {

        @Test
        @DisplayName("when adding two positive numbers")
        //@Disabled
        void testAddPositiveNumbers() {
            int expected = 2;
            int actual = mathUtils.add(1, 1);
            assertEquals(expected, actual, "should return the right sum");
        }

        @Test
        @DisplayName("when adding two negative numbers")
        void testAddNegativeNumbers() {
            int expected = -2;
            int actual = mathUtils.add(-1, -1);
            assertEquals(expected, actual, () -> "should return the right sum. " + expected + "but return "+actual);
        }
    }

    @RepeatedTest(3)
    @Tag("Circle")
    void testComputeCircleRadius(RepetitionInfo repetitionInfo) {
        if (repetitionInfo.getCurrentRepetition() == 2) {
            System.out.println("Moscar :-)");
            return;
        }
        assertEquals(
                314.1592653589793,
                mathUtils.computeCircleArea(10),
                "Should return right circle area"
        );
    }

    @Test
    @EnabledOnOs(OS.MAC)
    @Tag("Math")
    void testDivide() {
        boolean isServerUp = Boolean.FALSE;
        assumeTrue(!isServerUp);
        assertThrows(
                ArithmeticException.class,
                () -> mathUtils.divide(1,0),
                "Divide by zero should throw"
        );
    }

    /**
     * If one of the test fails the test fails
     */
    @Test
    @DisplayName("Multiply method")
    @Tag("Math")
    void testMultiply() {
        assertAll(
                () -> assertEquals(4, mathUtils.multiply(2,2), "should return 4"),
                () -> assertEquals(0, mathUtils.multiply(2,0), "should return 0"),
                () -> assertEquals(-2, mathUtils.multiply(2,-1), "should return -2")
        );
    }
}