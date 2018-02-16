package nl.uva.js.qlparser.logic;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import nl.uva.js.qlparser.models.enums.DataType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class TypeRegistry {
    @Setter(AccessLevel.NONE) private Map<String, DataType> variables = new HashMap<>();
}
