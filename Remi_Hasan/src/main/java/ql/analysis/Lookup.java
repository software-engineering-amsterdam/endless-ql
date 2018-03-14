package ql.analysis;

import ql.evaluation.Binding;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionIdentifier;
import ql.model.expression.variable.ExpressionVariableUndefined;

import java.util.ArrayList;
import java.util.List;

public class Lookup {
    public static Expression lookup(ExpressionIdentifier expression, List<Binding> bindings){
        return lookup(expression.identifier, bindings);
    }

    public static Expression lookup(String identifier, List<Binding> bindings){
        if(bindings.isEmpty()){
//            throw new UnsupportedOperationException("Cannot get value for unknown field '" + identifier + "'.");
            return new ExpressionVariableUndefined(null, null);
        }

        Binding binding = bindings.get(0);
        if(binding.name.equals(identifier)){
            return binding.expression;
        } else {
            List<Binding> otherBindings = new ArrayList<>();
            otherBindings.addAll(bindings);
            otherBindings.remove(binding);
            return lookup(identifier, otherBindings);
        }
    }
}
