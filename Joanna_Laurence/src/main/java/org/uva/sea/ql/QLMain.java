package org.uva.sea.ql;

public class QLMain {

    /**
     * TODO:
         Date with /
         lhs rhs

         Double dispatch for evaluator?
         int/int=decimal when needed
         rel: add . add << will return a list not a tree. // a - b - c. << terry group
         Support defining values to variables. See todo @ evaluator

        a && b << only eval b when a is true. etc

         Chaining of method calls

         Add add/div etc operators
         Move value items to other package
        String equal and date equal. (Lesser than and greater than for date, etc)

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
