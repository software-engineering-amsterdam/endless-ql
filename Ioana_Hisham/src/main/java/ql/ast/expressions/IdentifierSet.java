package ql.ast.expressions;

import java.util.HashSet;
import java.util.Set;

public class IdentifierSet {
    private final Set<Identifier> identifierSet = new HashSet<>();

    public boolean add(Identifier id) {
        return identifierSet.add(id);
    }

    public boolean exists(Identifier id) {
        return identifierSet.contains(id);
    }
}
