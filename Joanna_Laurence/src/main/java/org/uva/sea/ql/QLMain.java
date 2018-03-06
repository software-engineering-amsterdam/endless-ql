package org.uva.sea.ql;

public class QLMain {

    /**
     TODO:
     Add tests

     Date with /
     Show errors in GUI
     Show warnings in GUI

     Use correct types @ parsing. Leafs in AST should have other types?

     cyclic dependencies between questions
     No double questions in ql file. Only in if - else allowed

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
