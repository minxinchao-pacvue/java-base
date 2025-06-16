package com.example.function;

import io.vavr.control.Try;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

public class TryTest {

    Try<Integer> divide(Integer dividend, Integer divisor) {
        return Try.of(() -> dividend / divisor);
    }

    @Test
    void testDivide() {
        // Successful division
        Try<Integer> result1 = divide(10, 2);
        assert result1.isSuccess();
        assert result1.get() == 5;

        // Division by zero
        Try<Integer> result2 = divide(10, 0);
        assert result2.isFailure();
        assert result2.getCause() instanceof ArithmeticException;
    }
}
