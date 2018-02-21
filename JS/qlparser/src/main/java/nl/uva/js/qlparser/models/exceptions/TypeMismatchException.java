package nl.uva.js.qlparser.models.exceptions;

import lombok.*;
import nl.uva.js.qlparser.models.enums.DataType;

@RequiredArgsConstructor
public class TypeMismatchException extends RuntimeException {
    @NonNull private DataType expected;
    @NonNull private DataType got;

    @Override
    public String getMessage() {
        return "Expected " + expected + ", got " + got;
    }
}
