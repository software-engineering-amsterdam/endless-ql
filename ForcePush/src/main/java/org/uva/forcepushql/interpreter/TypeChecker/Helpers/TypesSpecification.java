package org.uva.forcepushql.interpreter.TypeChecker.Helpers;

import org.uva.forcepushql.parser.ast.NodeTypes;
import org.uva.forcepushql.parser.ast.elements.NumberNode;
import org.uva.forcepushql.parser.ast.elements.expressionnodes.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class TypesSpecification
{

    private Map<SpecificationKeys, NodeTypes> specification;

    public TypesSpecification()
    {
        this.fillSpecification();
    }

    public Map<SpecificationKeys, NodeTypes> getSpecification()
    {
        return this.specification;
    }

    private void fillSpecification()
    {

        this.specification = new HashMap<>();

        this.add(this.operators(NumberNode.class), NodeTypes.MONEY, NodeTypes.UNKNOWN, NodeTypes.MONEY);
        this.add(this.operators(NumberNode.class), NodeTypes.DECIMAL, NodeTypes.UNKNOWN, NodeTypes.DECIMAL);
        this.add(this.operators(NumberNode.class), NodeTypes.INT, NodeTypes.UNKNOWN, NodeTypes.INT);

        this.add(this.operators(NotNode.class), NodeTypes.BOOL, NodeTypes.UNKNOWN, NodeTypes.BOOL);

        this.add(this.operators(AndNode.class, OrNode.class), NodeTypes.BOOL, NodeTypes.BOOL, NodeTypes.BOOL);

        this.add(this.operators(IsEqualNode.class, NotEqualNode.class), NodeTypes.BOOL, NodeTypes.BOOL, NodeTypes.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class), NodeTypes.STR, NodeTypes.STR, NodeTypes.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class, EqualGreaterNode.class, GreaterNode.class, EqualLessNode.class, LessNode.class), NodeTypes.MONEY, NodeTypes.MONEY, NodeTypes.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class, EqualGreaterNode.class, GreaterNode.class, EqualLessNode.class, LessNode.class), NodeTypes.DECIMAL, NodeTypes.DECIMAL, NodeTypes.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class, EqualGreaterNode.class, GreaterNode.class, EqualLessNode.class, LessNode.class), NodeTypes.INT, NodeTypes.INT, NodeTypes.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class, EqualGreaterNode.class, GreaterNode.class, EqualLessNode.class, LessNode.class), NodeTypes.DECIMAL, NodeTypes.INT, NodeTypes.BOOL);
        this.add(this.operators(IsEqualNode.class, NotEqualNode.class, EqualGreaterNode.class, GreaterNode.class, EqualLessNode.class, LessNode.class), NodeTypes.INT, NodeTypes.DECIMAL, NodeTypes.BOOL);

        this.add(this.operators(DivisionNode.class), NodeTypes.INT, NodeTypes.INT, NodeTypes.DECIMAL);
        this.add(this.operators(DivisionNode.class), NodeTypes.MONEY, NodeTypes.MONEY, NodeTypes.DECIMAL);

        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class), NodeTypes.INT, NodeTypes.INT, NodeTypes.INT);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class, DivisionNode.class), NodeTypes.MONEY, NodeTypes.INT, NodeTypes.MONEY);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class, DivisionNode.class), NodeTypes.DECIMAL, NodeTypes.DECIMAL, NodeTypes.DECIMAL);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class, DivisionNode.class), NodeTypes.DECIMAL, NodeTypes.INT, NodeTypes.DECIMAL);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class, DivisionNode.class), NodeTypes.INT, NodeTypes.DECIMAL, NodeTypes.DECIMAL);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class, MultiplicationNode.class, DivisionNode.class), NodeTypes.MONEY, NodeTypes.DECIMAL, NodeTypes.MONEY);
        this.add(this.operators(AdditionNode.class, SubtractionNode.class), NodeTypes.MONEY, NodeTypes.MONEY, NodeTypes.MONEY);
    }

    private void add(Iterable<Class<?>> operatorTypes, NodeTypes left, NodeTypes right, NodeTypes returns)
    {
        for (Class operatorType : operatorTypes)
            this.specification.put(new SpecificationKeys(operatorType, left, right), returns);
    }

    private Iterable<Class<?>> operators(Class<?>... operatorTypes)
    {
        return Arrays.asList(operatorTypes);
    }
}


