package nl.uva.js.qlparser.exceptions;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nl.uva.js.qlparser.models.ql.enums.DataType;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TypeNotPossibleException extends RuntimeException {
    @NonNull private List<DataType> expected;
    @NonNull private DataType got;

    @Override
    public String getMessage() {
        return "Expected one of " + expected.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", ")) + ", got " + got;
    }
}
