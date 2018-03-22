using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.Analysis.QL.Evaluator;
using QLParser.AST;
using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using QLParser.AST.QL.ExpressionNodes.Enums;

namespace QL_Parser.Tests.Analysis.Evaluator
{
    [TestClass]
    public class StatementTypeEvaluatorTests
    {
        [TestMethod]
        public void GetStatementResultTypeMoneyTest()
        {
            var lhs = new LiteralNode(Location.Null, "10.0", QValueType.MONEY);
            var rhs = new LiteralNode(Location.Null, "5.0", QValueType.MONEY);
            Assert.AreEqual(QValueType.MONEY, StatementTypeEvaluator.GetStatementResultType(lhs, rhs));
        }

        [TestMethod]
        public void GetStatementResultTypeMoneyAndDoubleTest()
        {
            var lhs = new LiteralNode(Location.Null, "10.0", QValueType.MONEY);
            var rhs = new LiteralNode(Location.Null, "5.0", QValueType.DOUBLE);
            Assert.AreEqual(QValueType.MONEY, StatementTypeEvaluator.GetStatementResultType(lhs, rhs));
        }

        [TestMethod]
        public void GetStatementResulTypetDoubleTest()
        {
            var lhs = new LiteralNode(Location.Null, "10.0", QValueType.DOUBLE);
            var rhs = new LiteralNode(Location.Null, "5.0", QValueType.DOUBLE);
            Assert.AreEqual(QValueType.DOUBLE, StatementTypeEvaluator.GetStatementResultType(lhs, rhs));
        }

        [TestMethod]
        public void GetStatementResultTypeDoubleAndIntTest()
        {
            var lhs = new LiteralNode(Location.Null, "10.0", QValueType.DOUBLE);
            var rhs = new LiteralNode(Location.Null, "5", QValueType.INTEGER);
            Assert.AreEqual(QValueType.DOUBLE, StatementTypeEvaluator.GetStatementResultType(lhs, rhs));
        }

        [TestMethod]
        public void GetStatementResultTypeIntTest()
        {
            var lhs = new LiteralNode(Location.Null, "10.0", QValueType.INTEGER);
            var rhs = new LiteralNode(Location.Null, "5", QValueType.INTEGER);
            Assert.AreEqual(QValueType.INTEGER, StatementTypeEvaluator.GetStatementResultType(lhs, rhs));
        }

        [TestMethod]
        public void ExpressionNodeResultTypeDoubleTest()
        {
            var lhs = new LiteralNode(Location.Null, "10.0", QValueType.DOUBLE);
            var rhs = new LiteralNode(Location.Null, "5.0", QValueType.DOUBLE);
            var expression = new ArthimetricExpressionNode(Location.Null, lhs, ArthimetricOperator.MULT, rhs);
            Assert.AreEqual(QValueType.DOUBLE, expression.GetQValueType());
        }

        [TestMethod]
        public void ExpressionNodeResultTypeDoubleAndMoneyTest()
        {
            var lhs = new LiteralNode(Location.Null, "10.0", QValueType.MONEY);
            var rhs = new LiteralNode(Location.Null, "5.0", QValueType.DOUBLE);
            var expression = new ArthimetricExpressionNode(Location.Null, lhs, ArthimetricOperator.MULT, rhs);
            Assert.AreEqual(QValueType.MONEY, expression.GetQValueType());
        }

        [TestMethod]
        public void ExpressionNodeResultTypeIntAndMoneyTest()
        {
            var lhs = new LiteralNode(Location.Null, "10.0", QValueType.MONEY);
            var rhs = new LiteralNode(Location.Null, "5.0", QValueType.INTEGER);
            var expression = new ArthimetricExpressionNode(Location.Null, lhs, ArthimetricOperator.MULT, rhs);
            Assert.AreEqual(QValueType.MONEY, expression.GetQValueType());
        }

        [TestMethod]
        public void ExpressionNodeResultTypeDoubleAndIntTest()
        {
            var lhs = new LiteralNode(Location.Null, "10.0", QValueType.DOUBLE);
            var rhs = new LiteralNode(Location.Null, "5.0", QValueType.INTEGER);
            var expression = new ArthimetricExpressionNode(Location.Null, lhs, ArthimetricOperator.MULT, rhs);
            Assert.AreEqual(QValueType.DOUBLE, expression.GetQValueType());
        }
    }
}
