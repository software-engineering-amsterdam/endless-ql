package com.chariotit.uva.sc.qdsl.ast.common;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TypeCheckErrorComparatorTest {

    private Integer maxInt = 500;

    private TypeCheckError createError(String message, Integer line, Integer column) {
        return new TypeCheckError(message, new SourceFilePosition(line, column));
    }

    private Integer getRandomInt() {
        Random random = new Random();

        return random.nextInt(maxInt);
    }

    @Test
    public void testLineComparator() {
        List<TypeCheckError> errors = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            errors.add(createError(String.format("Error %d", i), getRandomInt(),
                    getRandomInt()));
        }

        errors.sort(new TypeCheckErrorComparator());

        for (int i = 0; i < errors.size() - 1; i++) {
            assertTrue(errors.get(i).getSourceFilePosition().getLineNumber() <=
                errors.get(i + 1).getSourceFilePosition().getLineNumber());
        }
    }

    @Test
    public void testColumnComparator() {
        List<TypeCheckError> errors = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            errors.add(createError(String.format("Error %d", i), 5, getRandomInt()));
        }

        errors.sort(new TypeCheckErrorComparator());

        for (int i = 0; i < errors.size() - 1; i++) {
            assertTrue(errors.get(i).getSourceFilePosition().getColumnNumber() <=
                    errors.get(i + 1).getSourceFilePosition().getColumnNumber());
        }
    }
}
