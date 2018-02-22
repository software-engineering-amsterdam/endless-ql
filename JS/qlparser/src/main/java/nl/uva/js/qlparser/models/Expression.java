package nl.uva.js.qlparser.models;

import com.vaadin.ui.Component;
import nl.uva.js.qlparser.models.enums.DataType;

import java.util.ArrayList;

public interface Expression {
    ArrayList<Component> getComponents();

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
