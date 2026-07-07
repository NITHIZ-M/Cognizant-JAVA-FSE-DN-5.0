package org.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test
    public void testAddition() {

        // Arrange
        Calculator calc = new Calculator();

        // Act
        int result = calc.add(10, 20);

        // Assert
        assertEquals(30, result);
    }
}