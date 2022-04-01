package tdd.exercises;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class StringCalculatorTest {

    StringCalculator calculator;

    RepositoryLogger repositoryLogger;

    @BeforeEach
    public void setup() {
        repositoryLogger = Mockito.mock(RepositoryLogger.class);
        calculator = new StringCalculator(repositoryLogger);
    }

    @Test
    public void whenStringIsEmpty_return0() {
        assertSumOfInputAndVerifyLogger("", 0);
    }

    @Test
    public void whenStringOneNumber_returnSameNumber() {
        assertSumOfInputAndVerifyLogger("1", 1);
        assertSumOfInputAndVerifyLogger("99", 99);
    }

    @Test
    public void whenInputHas2NumbersCommaSeparated_returnSum() {
        assertSumOfInputAndVerifyLogger("1,2", 3);
        assertSumOfInputAndVerifyLogger("11,21", 32);

    }

    @Test
    public void whenInputHasUnknownNumbersCommaSeparated_returnSumOfAll() {
        assertSumOfInputAndVerifyLogger("1,2,3,4", 10);
        assertSumOfInputAndVerifyLogger("11,21,10", 42);
    }

    @Test
    public void whenInputHasNewLineCharSeparator_returnSum() {
        assertSumOfInputAndVerifyLogger("1\n2,3", 6);
    }

    @Test
    public void whenCustomDelimiterIsProvided_returnSumSeparatedByCustomDelimiter() {
        assertSumOfInputAndVerifyLogger("//;\n1;2", 3);
    }

    @Test
    public void whenInputHasNegativeNumbers_throwException() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> calculator.add("//;\n1;-2;-3"))
                .withMessage("negatives not allowed: [-2, -3]");
        Mockito.verify(repositoryLogger).log("//;\n1;-2;-3");
    }

    private void assertSumOfInputAndVerifyLogger(String input, int expected) {
        Assertions.assertThat(calculator.add(input)).isEqualTo(expected);
        Mockito.verify(repositoryLogger).log(input);
    }
}