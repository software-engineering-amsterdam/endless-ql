package nl.uva.js.qlparser.models.expressions;

import nl.uva.js.qlparser.models.enums.DataType;

import java.awt.*;
import java.util.List;

public interface Expression {

    /**
     * Expressions that can by itself be visualized as a questionnaire component in html
     */
    interface Visualizable {
        List<Component> getComponents();
    }

    /*
     * Expressions that have a type and CAN have typed children
     */
    interface Typed {
        DataType returnCheckedType();
    }

    /*
     * Expressions that do not have a functional type, but DO have to evaluate their children.
     */
    interface TypeCheckable {
        void checkType();
    }
}
