package nl.uva.js.qlparser.models;

import nl.uva.js.qlparser.models.enums.DataType;

public interface Expression {
    void toRepresentation();

    /*
     * Expressions that have a type and CAN have typed children
     */
    interface Typed {
        DataType checkAndReturnType();
    }

    /*
     * Expressions that do not have a functional type, but DO have to evaluate their children.
     */
    interface TypeCheckable {
        void checkType();
    }
}
