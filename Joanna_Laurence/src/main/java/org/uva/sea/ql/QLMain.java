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

         Table for type checking. $1 / $3 = int   $1 * $1 != 1$ << impossible. 1$^2??
         Create a table. Operator per type. What is resulting type.
         Implement all the logic
         Remove the duplicate question check. Only else should be supported.

         int a = 5.0 / 3.0 << Has to be casted.

     */

    /**
     * Run the application in example.ql
     * @param args
     */
    public static void main(String[] args) {
        QLFormGenerator gui = new QLFormGenerator();
    }
}
