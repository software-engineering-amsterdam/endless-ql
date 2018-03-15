package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import org.uva.sea.languages.ql.interpreter.dataObject.SpecificationKey;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TypeCheckSpecification {

    private HashMap<SpecificationKey, NodeType> specification;

    public TypeCheckSpecification() {
        this.fillSpecification();
    }

    /**
     * Get the type specification
     *
     * @return
     */
    public HashMap<SpecificationKey, NodeType> getSpecification() {
        return specification;
    }

    /**
     * Read specification from file. The header contains the type order.
     * Every line start with operator and to which type it converts.
     * For example:
     * <p>
     * MoneyValue; 	DecimalValue;
     * Addition;	MoneyValue;	    DecimalValue;
     * And;			ErrorValue;	    ErrorValue;
     *
     * @return Lookup table
     */
    public void fillSpecification() {

        specification = new HashMap<>();

        add(operators(Positive.class, Negative.class), NodeType.MONEY_DOLLAR, NodeType.UNKNOWN, NodeType.MONEY_DOLLAR);
        add(operators(Positive.class, Negative.class), NodeType.MONEY_EURO, NodeType.UNKNOWN, NodeType.MONEY_EURO);
        add(operators(Positive.class, Negative.class), NodeType.DECIMAL, NodeType.UNKNOWN, NodeType.DECIMAL);
        add(operators(Positive.class, Negative.class), NodeType.INTEGER, NodeType.UNKNOWN, NodeType.INTEGER);

        add(operators(Not.class), NodeType.BOOLEAN, NodeType.UNKNOWN, NodeType.BOOLEAN);

        add(operators(And.class, Or.class), NodeType.BOOLEAN, NodeType.BOOLEAN, NodeType.BOOLEAN);

        add(operators(Equal.class, NotEqual.class), NodeType.BOOLEAN, NodeType.BOOLEAN, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class), NodeType.STRING, NodeType.STRING, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.MONEY_EURO, NodeType.MONEY_EURO, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.MONEY_DOLLAR, NodeType.MONEY_DOLLAR, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.DECIMAL, NodeType.DECIMAL, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.INTEGER, NodeType.INTEGER, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.DECIMAL, NodeType.INTEGER, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.INTEGER, NodeType.DECIMAL, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.DATE, NodeType.DATE, NodeType.BOOLEAN);

        add(operators(Division.class), NodeType.INTEGER, NodeType.INTEGER, NodeType.DECIMAL);
        add(operators(Division.class), NodeType.MONEY_DOLLAR, NodeType.MONEY_DOLLAR, NodeType.DECIMAL);
        add(operators(Division.class), NodeType.MONEY_EURO, NodeType.MONEY_EURO, NodeType.DECIMAL);
        add(operators(Addition.class, Subtraction.class, Multiplication.class), NodeType.INTEGER, NodeType.INTEGER, NodeType.INTEGER);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.MONEY_DOLLAR, NodeType.INTEGER, NodeType.MONEY_DOLLAR);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.MONEY_EURO, NodeType.INTEGER, NodeType.MONEY_EURO);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.DECIMAL, NodeType.DECIMAL, NodeType.DECIMAL);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.DECIMAL, NodeType.INTEGER, NodeType.DECIMAL);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.INTEGER, NodeType.DECIMAL, NodeType.DECIMAL);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.MONEY_DOLLAR, NodeType.DECIMAL, NodeType.MONEY_DOLLAR);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.MONEY_EURO, NodeType.DECIMAL, NodeType.MONEY_EURO);
        add(operators(Addition.class, Subtraction.class), NodeType.MONEY_DOLLAR, NodeType.MONEY_DOLLAR, NodeType.MONEY_DOLLAR);
        add(operators(Addition.class, Subtraction.class), NodeType.MONEY_EURO, NodeType.MONEY_EURO, NodeType.MONEY_EURO);
    }

    /**
     * Add element to the specification table
     *
     * @param operatorTypes Operators
     * @param left          Left side type
     * @param right         Right side type
     * @param returns       Returns new type
     */
    private void add(List<Class<?>> operatorTypes, NodeType left, NodeType right, NodeType returns) {
        for (Class operatorType : operatorTypes)
            specification.put(new SpecificationKey(operatorType, left, right), returns);
    }

    /**
     * Convert var args to list
     *
     * @param operatorTypes Operator args
     * @return The list of operators
     */
    private List<Class<?>> operators(Class<?>... operatorTypes) {
        return Arrays.asList(operatorTypes);
    }
}
