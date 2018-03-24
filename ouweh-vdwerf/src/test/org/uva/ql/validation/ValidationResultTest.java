package org.uva.ql.validation;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ValidationResultTest {

    @Test
    public void merge() {
        ValidationResult result1 = new ValidationResult();
        ValidationResult result2 = new ValidationResult();

        result1.addError("One");
        result2.addError("Two");
        ValidationResult merged = result1.merge(result2);

        assertTrue(merged.getErrors().size() == 2);
    }
}