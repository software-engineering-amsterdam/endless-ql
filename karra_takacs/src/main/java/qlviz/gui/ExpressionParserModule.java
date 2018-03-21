package qlviz.gui;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import qlviz.QLBaseVisitor;
import qlviz.interpreter.*;
import qlviz.model.booleanExpressions.BooleanExpression;
import qlviz.model.numericExpressions.NumericExpression;

public class ExpressionParserModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(BinaryNumericOperatorTranslator.class).to(BinaryNumericOperatorVisitor.class);
		bind(new TypeLiteral<QLBaseVisitor<NumericExpression>>(){}).to(NumericExpressionParser.class);
		bind(new TypeLiteral<QLBaseVisitor<BooleanExpression>>(){}).to(BooleanExpressionParser.class);
		bind(BinaryBooleanOperatorTranslator.class).to(BinaryBooleanOperatorVisitor.class);
		bind(NumericComparisonOperatorTranslator.class).to(NumericComparisonOperatorVisitor.class);
	}
}
