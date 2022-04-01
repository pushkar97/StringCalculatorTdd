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
        Assertions.assertThat(calculator.add("")).isEqualTo(0);
    }

    @Test
    public void whenStringOneNumber_returnSameNumber() {
        Assertions.assertThat(calculator.add("1")).isEqualTo(1);
        Assertions.assertThat(calculator.add("99")).isEqualTo(99);
    }

    @Test
    public void whenInputHas2NumbersCommaSeparated_returnSum() {
        Assertions.assertThat(calculator.add("1,2")).isEqualTo(3);
        Assertions.assertThat(calculator.add("11,21")).isEqualTo(32);
    }

    @Test
    public void whenInputHasUnknownNumbersCommaSeparated_returnSumOfAll() {
        Assertions.assertThat(calculator.add("1,2,3,4")).isEqualTo(10);
        Assertions.assertThat(calculator.add("11,21,10")).isEqualTo(42);
    }

    @Test
    public void whenInputHasNewLineCharSeparator_returnSum() {
        Assertions.assertThat(calculator.add("1\n2,3")).isEqualTo(6);
    }

    @Test
    public void whenCustomDelimiterIsProvided_returnSumSeparatedByCustomDelimiter() {
        Assertions.assertThat(calculator.add("//;\n1;2")).isEqualTo(3);
    }

    @Test
    public void whenInputHasNegativeNumbers_throwException() {
        Assertions.assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> calculator.add("//;\n1;-2;-3"))
                .withMessage("negatives not allowed: [-2, -3]");
    }

    @Test
    public void whenAddInvoked_logInputToLogger() {
        String input = "//;\n1;2";
        //when
        calculator.add(input);
        //Then
        Mockito.verify(repositoryLogger).log(input);
    }
}