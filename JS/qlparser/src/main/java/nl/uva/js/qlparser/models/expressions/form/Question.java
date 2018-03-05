package nl.uva.js.qlparser.models.expressions.form;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import nl.uva.js.qlparser.helpers.NonNullRun;
import nl.uva.js.qlparser.models.expressions.data.DataExpression;
import nl.uva.js.qlparser.models.enums.DataType;
import nl.uva.js.qlparser.exceptions.TypeMismatchException;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class Question implements FormExpression {
    @NonNull private String name;
    @NonNull private String question;
    @NonNull private DataType dataType;
    private DataExpression value;

    private static final Map<String, String> mapDataTypeToHtml = new HashMap<String, String>() {
        {
            put(DataType.BOOLEAN.getTypeString(), "\"checkbox\"");
            put(DataType.DATE.getTypeString(),    "\"date\"");
            put(DataType.DECIMAL.getTypeString(), "\"number\" step=\".1\"");
            put(DataType.MONEY.getTypeString(),   "\"number\" step=\".01\"");
            put(DataType.INTEGER.getTypeString(), "\"number\"");
            put(DataType.STRING.getTypeString(),  "\"text\"");
        }
    };

    @Override
    public List<String> getComponents() {
        return Collections.singletonList(dataType.getTypeString());
    }

    public String getHtmlType() {
        return mapDataTypeToHtml.get(dataType.getTypeString());
    }

    @Override
    public void checkType() {
        NonNullRun.consumer(value, v -> {
            DataType inferredType = v.checkAndReturnType();
            if (inferredType != dataType)
                throw new TypeMismatchException(dataType, inferredType);
        });
    }
}
