package nl.uva.js.qlparser.exceptions;

import lombok.*;
import nl.uva.js.qlparser.models.ql.enums.DataType;

@RequiredArgsConstructor
public class TypeMismatchException extends RuntimeException {
    @NonNull private DataType expected;
    @NonNull private DataType got;

    @Override
    public String getMessage() {
        return "Expected " + expected + ", got " + got;
    }
}
