package tdd.exercises;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StringCalculatorTest {

    StringCalculator calculator;

    @BeforeEach
    public void setup() {calculator = new StringCalculator();}

    @Test
    public void testSetup() {
        Assertions.assertThat(calculator).isNotNull();
    }
}