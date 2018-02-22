package org.uva.sea.ql;

public class QLMain {

    /**
     * TODO:
         Date with /
         Rename mul add etc
         set location <- constructor
         lhs rhs
         rename to visit pattern << doX to visit

         Double dispatch for evaluator?
         int/int=decimal when needed
         rel: add . add << will return a list not a tree. // a - b - c. << terry group
         // Binary expression - operation as name? Instead of condition and logical? logical = binary operator
         Support defining values to variables. See todo @ evaluator

        Are question values computed on the first level?
        //WHen value is defined, make immutible
        a && b << only eval b when a is true. etc

         Enum methods for number
         Error list
         Chaining of method calls

         Return Value types
         Create value types for everything
         Add add/div etc operators

     */

    /**
     * Run the application in example.ql
     * @param args
     */
    public static void main(String[] args) {
        QLGui gui = new QLGui();
        gui.start("/example.ql");
    }
}
