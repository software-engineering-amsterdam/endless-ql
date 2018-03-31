package ql.evaluator;

import ql.ast.expressions.Identifier;
import ql.values.Undefined;
import ql.values.Value;

import java.util.HashMap;
import java.util.Map;

public class ValueTable {
    private final Map<String, Value> values = new HashMap<>();

    public Value add(Identifier id, Value value) {
        return values.put(id.toString(), value);
    }

    public Value find(Identifier id) {
        if (!this.exists(id)) {
            return new Undefined();
        }
        return values.get(id.toString());
    }

    public boolean exists(Identifier id) {
        return values.containsKey(id.toString());
    }
}
