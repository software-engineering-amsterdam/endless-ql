package Nodes.Operator;

import Nodes.Term.Boolean;

public class BooleanOperation extends Operator {


    public BooleanOperation(String value){
        super(value);
    }

    // TODO Use Term instead of Boolean
    public Boolean calculate (Boolean left, Boolean right) {
        if(this.getValue().equals("&&"))
            return new Boolean(left.getBoolean() && right.getBoolean());
        if(this.getValue().equals("||"))
            return new Boolean(left.getBoolean() && right.getBoolean());

        // TODO throw exception if codes get here
        return new Boolean(false);
    }
}
