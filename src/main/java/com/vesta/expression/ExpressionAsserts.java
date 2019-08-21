package com.vesta.expression;

import java.util.function.Supplier;

public class ExpressionAsserts {

    private ExpressionAsserts() {
        throw new IllegalStateException("Utility class");
    }

    public static <T extends RuntimeException> void verify(Boolean value, Supplier<T> supplier) {
        if (value) {
            throw supplier.get();
        }
    }
}
