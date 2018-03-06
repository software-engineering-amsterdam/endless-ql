package test.ql;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import org.junit.Assert;
import ast.expression.*;
import ast.type.*;
import ast.parser.*;
import visiting.EvaluationVisitor;
import visiting.value.*;

public class EvaluationTest {
	
	/**
	 * calls evaluation method in EvaluationVisitor to return objects such as Value
	 * so that it gets compared in different test methods against the exprected value
	 * @param: expressionStr
	 * @return: object
	 */
	private void Object evaluate(String expressionStr) throws IOException {
		return EvaluationVisitor.evaluate(createExpression(expressionStr), null);
	}

	/**
	 *  returns an Expression to be passed for the evaluator
	 * @param expressionStr
	 * @return Expression
	 * @throws IOException
	 */
	public static Expression createExpression(String expressionStr) throws IOException {
		TokenStream tokenStream = new CommonTokenStream(new QLLexer(new ANTLRInputStream(expressionStr)));
		QLParser parser = new QLParser(tokenStream);
		return parser.expr().result;
	}
	
	/** 
	 * test methods of values such as add, sub, or
	 * @return void
	 * @throws IOException
	 */
	@Test
	public void valueMethodsTest() throws IOException {
		/* logical expressions */
		Assert.assertEquals(BooleanValue.TRUE, evaluate("true || true"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("false || false"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("true || false"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("false || true"));

		Assert.assertEquals(BooleanValue.TRUE, evaluate("true && true"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("false && false"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("true && false"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("false && true"));

		Assert.assertEquals(BooleanValue.FALSE, evaluate("!true"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("!false"));
		
		/* literals */
		Assert.assertEquals(BooleanValue.TRUE, evaluate("true"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("yes"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("false"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("no"));
		
		Assert.assertEquals(new IntegerValue(20), evaluate("20"));
		Assert.assertEquals(new IntegerValue(21), evaluate("21"));

		Assert.assertEquals(new StringValue("testing evaluation visitor"), evaluate("\"text\""));

		/* mathematical-arithmetics */
		//add
		Assert.assertEquals(new IntegerValue(3), evaluate("1+2"));
		Assert.assertEquals(new IntegerValue(3), evaluate("(1+2)"));

		//sub
		Assert.assertEquals(new IntegerValue(-1), evaluate("1-2"));
		Assert.assertEquals(new IntegerValue(-1), evaluate("(1-2)"));

		//mul
		Assert.assertEquals(new IntegerValue(2), evaluate("1*2"));
		Assert.assertEquals(new IntegerValue(-2), evaluate("-1*2"));
		Assert.assertEquals(new IntegerValue(2), evaluate("-1*-2"));
		Assert.assertEquals(new IntegerValue(-2), evaluate("1*-2"));
		Assert.assertEquals(new IntegerValue(2), evaluate("(1*2)"));
		Assert.assertEquals(new IntegerValue(-2), evaluate("-(1*2)"));

		//div
		Assert.assertEquals(new IntegerValue(5), evaluate("10/2"));
		Assert.assertEquals(new IntegerValue(6), evaluate("(12/2)"));

		//GT
		Assert.assertEquals(BooleanValue.TRUE, evaluate("1>0"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("0>0"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("0>1"));

		//GEq
		Assert.assertEquals(BooleanValue.TRUE, evaluate("0>=0"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("1>=0"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("1>=0"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("0>=2"));

		//LT
		Assert.assertEquals(BooleanValue.FALSE, evaluate("0<0"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("0<1"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("1<0"));
		
		//LEq
		Assert.assertEquals(BooleanValue.TRUE, evaluate("1<=2"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("1<=1"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("1<=0"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("1<=-1"));

		//Eq - NEq
		Assert.assertEquals(BooleanValue.TRUE, evaluate("0==0"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("\"aa\"==\"aa\""));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("\"aa\"!=\"bb\""));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("\"cc\"!=\"dd\""));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("1.2==0.0"));
	}

	/** 
	 * tests evaulating different types of expressions using assertEquals
	 * @return void
	 * @throws IOException
	 */
	@Test
	public void evaluateExpressionsTest() throws IOException {
		// arithmetic expressions evaluation
		Assert.assertEquals(new IntegerValue(14), evaluate("(1+6)*2"));
		Assert.assertEquals(new IntegerValue(14), evaluate("2+2*6"));
		Assert.assertEquals(new IntegerValue(3), evaluate("2--(2-1)"));
		Assert.assertEquals(new IntegerValue(1), evaluate("1+1*0"));
		Assert.assertEquals(new IntegerValue(0), evaluate("1*0"));
		Assert.assertEquals(new IntegerValue(0), evaluate("1-1"));
		Assert.assertEquals(new IntegerValue(0), evaluate("1 -1"));
		Assert.assertEquals(new IntegerValue(-14), evaluate("-14"));
		Assert.assertEquals(new IntegerValue(-28), evaluate("-14-14"));
		Assert.assertEquals(new IntegerValue(-28), evaluate("-(14+14)"));
		Assert.assertEquals(new IntegerValue(-8), evaluate("-1*(4*2)"));
		Assert.assertEquals(new IntegerValue(-2), evaluate("-1*(4/2)"));
		Assert.assertEquals(new IntegerValue(-4), evaluate("-1*(4*2/2)"));

		//logical evaluation
		Assert.assertEquals(BooleanValue.TRUE, evaluate("true || true && true || false"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("true || false && true || false"));
		
		Assert.assertEquals(BooleanValue.FALSE, evaluate("(false || false) && (false || true)"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("(false || false) && (true || false)"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("(false || false) && (false || false)"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("false || true && false"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("false || (true && false)"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("(false || true) && false"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("false || (false && true) || false"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("false || false && true || false"));

		//comparision & logical evaluation
		Assert.assertEquals(BooleanValue.TRUE, evaluate("1 > 2 || 2 > 1"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("(0*(14*4/2)) == 0"));
		Assert.assertEquals(BooleanValue.TRUE, evaluate("true || 0 > 1"));
		
		Assert.assertEquals(BooleanValue.FALSE, evaluate("1 >(1*1) || false"));
		Assert.assertEquals(BooleanValue.FALSE, evaluate("0 > 1 && 1 > 0"));
	}
}

