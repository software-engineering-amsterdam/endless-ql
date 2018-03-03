package org.uva.sea.ql;

public class QLMain {

    /**
     TODO:
     Date with /
     Rename lhs rhs
     String equal and date equal. (Lesser than and greater than for date, etc)
     Show errors in GUI

     cyclic dependencies between questions
     duplicate labels (warning)
     duplicate question declarations with different types

     Implement all the correct logic
     Remove the duplicate question check. Only else should be supported.

     int a = 5.0 / 3.0 << Has to be casted.

     >> NOT Correct? We had one scope?
     Scope for statements. Inner scope can have a question, do not use outside.
     Create a list of scopes. Every new scope, push a new scoop. Link vars to current scope, or the parent scopes.
     When no ref in any scope, error.
     Now you can also have if {} else {} with in if and else same questions.
     <<

     */

    /**
     * Run the application in example.ql
     *
     * @param args
     */
    public static void main(String[] args) {
        QLSpecificationEvaluator gui = new QLSpecificationEvaluator();
    }
}
