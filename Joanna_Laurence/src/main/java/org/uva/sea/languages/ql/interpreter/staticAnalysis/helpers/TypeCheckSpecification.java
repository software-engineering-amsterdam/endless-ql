package org.uva.sea.languages.ql.interpreter.staticAnalysis.helpers;

import org.uva.sea.languages.ql.interpreter.dataObject.SpecificationKey;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    public Map<SpecificationKey, NodeType> getSpecification() {
        return this.specification;
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
    private void fillSpecification() {

        this.specification = new HashMap<>();

        this.add(this.operators(Positive.class, Negative.class), NodeType.MONEY_DOLLAR, NodeType.UNKNOWN, NodeType.MONEY_DOLLAR);
        this.add(this.operators(Positive.class, Negative.class), NodeType.MONEY_EURO, NodeType.UNKNOWN, NodeType.MONEY_EURO);
        this.add(this.operators(Positive.class, Negative.class), NodeType.DECIMAL, NodeType.UNKNOWN, NodeType.DECIMAL);
        this.add(this.operators(Positive.class, Negative.class), NodeType.INTEGER, NodeType.UNKNOWN, NodeType.INTEGER);

        this.add(this.operators(Not.class), NodeType.BOOLEAN, NodeType.UNKNOWN, NodeType.BOOLEAN);

        this.add(this.operators(And.class, Or.class), NodeType.BOOLEAN, NodeType.BOOLEAN, NodeType.BOOLEAN);

        this.add(this.operators(Equal.class, NotEqual.class), NodeType.BOOLEAN, NodeType.BOOLEAN, NodeType.BOOLEAN);
        this.add(this.operators(Equal.class, NotEqual.class), NodeType.STRING, NodeType.STRING, NodeType.BOOLEAN);
        this.add(this.operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.MONEY_EURO, NodeType.MONEY_EURO, NodeType.BOOLEAN);
        this.add(this.operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.MONEY_DOLLAR, NodeType.MONEY_DOLLAR, NodeType.BOOLEAN);
        this.add(this.operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.DECIMAL, NodeType.DECIMAL, NodeType.BOOLEAN);
        this.add(this.operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.INTEGER, NodeType.INTEGER, NodeType.BOOLEAN);
        this.add(this.operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.DECIMAL, NodeType.INTEGER, NodeType.BOOLEAN);
        this.add(this.operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.INTEGER, NodeType.DECIMAL, NodeType.BOOLEAN);
        this.add(this.operators(Equal.class, NotEqual.class, GreaterOrEqual.class, GreaterThan.class, LessOrEqual.class, LessThan.class), NodeType.DATE, NodeType.DATE, NodeType.BOOLEAN);

        this.add(this.operators(Division.class), NodeType.INTEGER, NodeType.INTEGER, NodeType.DECIMAL);
        this.add(this.operators(Division.class), NodeType.MONEY_DOLLAR, NodeType.MONEY_DOLLAR, NodeType.DECIMAL);
        this.add(this.operators(Division.class), NodeType.MONEY_EURO, NodeType.MONEY_EURO, NodeType.DECIMAL);
        this.add(this.operators(Addition.class, Subtraction.class, Multiplication.class), NodeType.INTEGER, NodeType.INTEGER, NodeType.INTEGER);
        this.add(this.operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.MONEY_DOLLAR, NodeType.INTEGER, NodeType.MONEY_DOLLAR);
        this.add(this.operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.MONEY_EURO, NodeType.INTEGER, NodeType.MONEY_EURO);
        this.add(this.operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.DECIMAL, NodeType.DECIMAL, NodeType.DECIMAL);
        this.add(this.operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.DECIMAL, NodeType.INTEGER, NodeType.DECIMAL);
        this.add(this.operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.INTEGER, NodeType.DECIMAL, NodeType.DECIMAL);
        this.add(this.operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.MONEY_DOLLAR, NodeType.DECIMAL, NodeType.MONEY_DOLLAR);
        this.add(this.operators(Addition.class, Subtraction.class, Multiplication.class, Division.class), NodeType.MONEY_EURO, NodeType.DECIMAL, NodeType.MONEY_EURO);
        this.add(this.operators(Addition.class, Subtraction.class), NodeType.MONEY_DOLLAR, NodeType.MONEY_DOLLAR, NodeType.MONEY_DOLLAR);
        this.add(this.operators(Addition.class, Subtraction.class), NodeType.MONEY_EURO, NodeType.MONEY_EURO, NodeType.MONEY_EURO);
    }

    /**
     * Add element to the specification table
     *
     * @param operatorTypes Operators
     * @param left          Left side type
     * @param right         Right side type
     * @param returns       Returns new type
     */
    private void add(Iterable<Class<?>> operatorTypes, NodeType left, NodeType right, NodeType returns) {
        for (Class operatorType : operatorTypes)
            this.specification.put(new SpecificationKey(operatorType, left, right), returns);
    }

    /**
     * Convert var args to list
     *
     * @param operatorTypes Operator args
     * @return The list of operators
     */
    private Iterable<Class<?>> operators(Class<?>... operatorTypes) {
        return Arrays.asList(operatorTypes);
    }
}
