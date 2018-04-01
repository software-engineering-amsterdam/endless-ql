package org.uva.forcepushql.interpreter.TypeChecker.Helpers;

import org.uva.forcepushql.parser.ast.ValueType;
import org.uva.forcepushql.parser.ast.elements.NumberNode;
import org.uva.forcepushql.parser.ast.elements.expressionnodes.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class TypesSpecification
{

    private Map<SpecificationKeys, ValueType> specification;

    public TypesSpecification()
    {
        this.fillSpecification();
    }

    public Map<SpecificationKeys, ValueType> getSpecification()
    {
        return this.specification;
    }

    private void fillSpecification()
    {

        this.specification = new HashMap<>();

        this.add(this.operators(NumberNode.class), ValueType.MONEY, ValueType.UNKNOWN, ValueType.MONEY);
        this.add(this.operators(NumberNode.class), ValueType.DECIMAL, ValueType.UNKNOWN, ValueType.DECIMAL);
        this.add(this.operators(NumberNode.class), ValueType.INT, ValueType.UNKNOWN, ValueType.INT);

        this.add(this.operators(NotNode.class), ValueType.BOOL, ValueType.UNKNOWN, ValueType.BOOL);

        this.add(this.operators(AndNode.class, OrNode.class), ValueType.BOOL, ValueType.BOOL, ValueType.BOOL);

        this.add(this.operators(IsEqualNode.class, NotEqualNode.class), ValueType.BOOL, ValueType.BOOL, ValueType.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class), ValueType.STR, ValueType.STR, ValueType.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class, EqualGreaterNode.class, GreaterNode.class, EqualLessNode.class, LessNode.class), ValueType.MONEY, ValueType.MONEY, ValueType.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class, EqualGreaterNode.class, GreaterNode.class, EqualLessNode.class, LessNode.class), ValueType.DECIMAL, ValueType.DECIMAL, ValueType.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class, EqualGreaterNode.class, GreaterNode.class, EqualLessNode.class, LessNode.class), ValueType.INT, ValueType.INT, ValueType.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class, EqualGreaterNode.class, GreaterNode.class, EqualLessNode.class, LessNode.class), ValueType.DECIMAL, ValueType.INT, ValueType.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class, EqualGreaterNode.class, GreaterNode.class, EqualLessNode.class, LessNode.class), ValueType.INT, ValueType.DECIMAL, ValueType.BOOL);

        this.add(this.operators(DivisionNode.class), ValueType.INT, ValueType.INT, ValueType.DECIMAL);
        this.add(this.operators(DivisionNode.class), ValueType.MONEY, ValueType.MONEY, ValueType.DECIMAL);

        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class), ValueType.INT, ValueType.INT, ValueType.INT);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class, DivisionNode.class), ValueType.MONEY, ValueType.INT, ValueType.MONEY);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class, DivisionNode.class), ValueType.DECIMAL, ValueType.DECIMAL, ValueType.DECIMAL);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class, DivisionNode.class), ValueType.DECIMAL, ValueType.INT, ValueType.DECIMAL);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class, DivisionNode.class), ValueType.INT, ValueType.DECIMAL, ValueType.DECIMAL);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class, DivisionNode.class), ValueType.MONEY, ValueType.DECIMAL, ValueType.MONEY);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class), ValueType.MONEY, ValueType.MONEY, ValueType.MONEY);
    }

    private void add(Iterable<Class<?>> operatorTypes, ValueType left, ValueType right, ValueType returns)
    {
        for (Class operatorType : operatorTypes)
            this.specification.put(new SpecificationKeys(operatorType, left, right), returns);
    }

    private Iterable<Class<?>> operators(Class<?>... operatorTypes)
    {
        return Arrays.asList(operatorTypes);
    }
}


