package tdd.exercises;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    StringCalculator calculator;

    @BeforeEach
    public void setup() {calculator = new StringCalculator();}

    @Test
    public void whenStringIsEmpty_return0() {
        Assertions.assertThat(calculator.add("")).isEqualTo(0);
    }

    @Test
    public void whenStringOneNumber_returnSameNumber() {
        Assertions.assertThat(calculator.add("1")).isEqualTo(1);
        Assertions.assertThat(calculator.add("2")).isEqualTo(2);
        Assertions.assertThat(calculator.add("99")).isEqualTo(99);
    }
}