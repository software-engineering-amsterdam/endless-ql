package ql.evaluator;

import ql.ast.expressions.Identifier;
import ql.values.Undefined;
import ql.values.Value;

import java.util.HashMap;
import java.util.Map;

public class ValueTable {
    private final Map<String, Value> values = new HashMap<>();

    public Value add(String id, Value value) {
        return values.put(id, value);
    }

    public Value find(Identifier id) {
        if (!values.containsKey(id.toString())) {
            return new Undefined();
        }
        return values.get(id.toString());
    }
}
