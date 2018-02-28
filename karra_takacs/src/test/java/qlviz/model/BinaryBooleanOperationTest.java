package qlviz.model;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Assert;
import org.junit.Test;
import qlviz.model.booleanExpressions.BinaryBooleanOperation;
import qlviz.model.booleanExpressions.BinaryBooleanOperator;
import qlviz.model.booleanExpressions.BooleanExpression;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BinaryBooleanOperationTest {

    @Test
    public void testEvaluateAnd() {
        // Arrange
        BooleanExpression falseMock = mock(BooleanExpression.class);
        BooleanExpression trueMock = mock(BooleanExpression.class);
        when(falseMock.evaluate()).thenReturn(false);
        when(trueMock.evaluate()).thenReturn(true);

        List<Triple<BooleanExpression, BooleanExpression, Boolean>> cases = Lists.newArrayList(
            Triple.of(trueMock, trueMock, true),
            Triple.of(trueMock, falseMock, false),
            Triple.of(falseMock, trueMock, false),
            Triple.of(falseMock, falseMock, false)
        );

        List<Pair<BinaryBooleanOperation, Boolean>> operations = cases.stream().map(c -> Pair.of(new BinaryBooleanOperation(c.getLeft(), c.getMiddle(), BinaryBooleanOperator.And), c.getRight())).collect(Collectors.toList());

        // Act

        // Assert
        operations.stream().forEach(op -> Assert.assertEquals(op.getRight(), op.getLeft().evaluate()));
    }

    @Test
    public void testEvaluateOr() {
        // Arrange
        BooleanExpression falseMock = mock(BooleanExpression.class);
        BooleanExpression trueMock = mock(BooleanExpression.class);
        when(falseMock.evaluate()).thenReturn(false);
        when(trueMock.evaluate()).thenReturn(true);

        List<Triple<BooleanExpression, BooleanExpression, Boolean>> cases = Lists.newArrayList(
            Triple.of(trueMock, trueMock, true),
            Triple.of(trueMock, falseMock, true),
            Triple.of(falseMock, trueMock, true),
            Triple.of(falseMock, falseMock, false)
        );

        List<Pair<BinaryBooleanOperation, Boolean>> operations = cases.stream().map(c -> Pair.of(new BinaryBooleanOperation(c.getLeft(), c.getMiddle(), BinaryBooleanOperator.Or), c.getRight())).collect(Collectors.toList());

        // Act

        // Assert
        operations.stream().forEach(op -> Assert.assertEquals(op.getRight(), op.getLeft().evaluate()));
    }
}