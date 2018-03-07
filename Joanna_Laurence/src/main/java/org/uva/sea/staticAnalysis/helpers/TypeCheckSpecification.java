package org.uva.sea.staticAnalysis.helpers;

import org.uva.sea.ql.elements.expressions.*;
import org.uva.sea.dataObject.SpecificationKey;
import org.uva.sea.ql.NodeType;

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

        add(operators(Positive.class, Negative.class), NodeType.MONEY, NodeType.UNKNOWN, NodeType.MONEY);
        add(operators(Positive.class, Negative.class), NodeType.DECIMAL, NodeType.UNKNOWN, NodeType.DECIMAL);
        add(operators(Positive.class, Negative.class), NodeType.INTEGER, NodeType.UNKNOWN, NodeType.INTEGER);

        add(operators(Not.class), NodeType.BOOLEAN, NodeType.UNKNOWN, NodeType.BOOLEAN);

        add(operators(And.class, Or.class), NodeType.BOOLEAN, NodeType.BOOLEAN, NodeType.BOOLEAN);

        add(operators(Equal.class, NotEqual.class), NodeType.BOOLEAN, NodeType.BOOLEAN, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class), NodeType.STRING, NodeType.STRING, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.MONEY, NodeType.MONEY, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.DECIMAL, NodeType.DECIMAL, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.INTEGER, NodeType.INTEGER, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.DECIMAL, NodeType.INTEGER, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.INTEGER, NodeType.DECIMAL, NodeType.BOOLEAN);
        add(operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.DATE, NodeType.DATE, NodeType.BOOLEAN);

        add(operators(Division.class), NodeType.INTEGER, NodeType.INTEGER, NodeType.DECIMAL);
        add(operators(Division.class), NodeType.MONEY, NodeType.MONEY, NodeType.DECIMAL);
        add(operators(Addition.class, Subtraction.class, Multiplication.class), NodeType.INTEGER, NodeType.INTEGER, NodeType.INTEGER);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.MONEY, NodeType.INTEGER, NodeType.MONEY);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.DECIMAL, NodeType.DECIMAL, NodeType.DECIMAL);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.DECIMAL, NodeType.INTEGER, NodeType.DECIMAL);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.INTEGER, NodeType.DECIMAL, NodeType.DECIMAL);
        add(operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.MONEY, NodeType.DECIMAL, NodeType.MONEY);
        add(operators(Addition.class, Subtraction.class), NodeType.MONEY, NodeType.MONEY, NodeType.MONEY);
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
