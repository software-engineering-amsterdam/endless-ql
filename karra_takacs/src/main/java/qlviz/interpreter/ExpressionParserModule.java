package qlviz.interpreter;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import qlviz.QLVisitor;
import qlviz.model.expressions.booleanExpressions.BooleanExpression;
import qlviz.model.expressions.numericExpressions.NumericExpression;

public class ExpressionParserModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(BinaryNumericOperatorTranslator.class).to(BinaryNumericOperatorVisitor.class);
		bind(new TypeLiteral<QLVisitor<NumericExpression>>(){}).to(NumericExpressionParser.class);
		bind(new TypeLiteral<QLVisitor<BooleanExpression>>(){}).to(BooleanExpressionParser.class);
		bind(BinaryBooleanOperatorTranslator.class).to(BinaryBooleanOperatorVisitor.class);
		bind(NumericComparisonOperatorTranslator.class).to(NumericComparisonOperatorVisitor.class);
	}
}

