package ql.typechecker;

import ql.ast.expressions.Identifier;
import ql.types.Type;
import ql.values.Undefined;

import java.util.HashMap;
import java.util.Map;

public class DeclarationTable {
    private final Map<String, Type> declarations = new HashMap<>();

    public Type add(Identifier id, Type value) {
        return declarations.put(id.toString(), value);
    }

    public Type find(Identifier id) {
        return declarations.get(id.toString());
    }

    public boolean exists(Identifier id) {
        return declarations.containsKey(id.toString());
    }
}
