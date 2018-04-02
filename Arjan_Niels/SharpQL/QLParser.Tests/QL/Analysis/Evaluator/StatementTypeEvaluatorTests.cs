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
            var lhs = new LiteralNode(Location.Empty, "10.0", QValueType.Money);
            var rhs = new LiteralNode(Location.Empty, "5.0", QValueType.Money);
            Assert.AreEqual(QValueType.Money, StatementTypeEvaluator.GetStatementResultType(lhs, rhs));
        }

        [TestMethod]
        public void GetStatementResultTypeMoneyAndDoubleTest()
        {
            var lhs = new LiteralNode(Location.Empty, "10.0", QValueType.Money);
            var rhs = new LiteralNode(Location.Empty, "5.0", QValueType.Double);
            Assert.AreEqual(QValueType.Money, StatementTypeEvaluator.GetStatementResultType(lhs, rhs));
        }

        [TestMethod]
        public void GetStatementResulTypetDoubleTest()
        {
            var lhs = new LiteralNode(Location.Empty, "10.0", QValueType.Double);
            var rhs = new LiteralNode(Location.Empty, "5.0", QValueType.Double);
            Assert.AreEqual(QValueType.Double, StatementTypeEvaluator.GetStatementResultType(lhs, rhs));
        }

        [TestMethod]
        public void GetStatementResultTypeDoubleAndIntTest()
        {
            var lhs = new LiteralNode(Location.Empty, "10.0", QValueType.Double);
            var rhs = new LiteralNode(Location.Empty, "5", QValueType.Integer);
            Assert.AreEqual(QValueType.Double, StatementTypeEvaluator.GetStatementResultType(lhs, rhs));
        }

        [TestMethod]
        public void GetStatementResultTypeIntTest()
        {
            var lhs = new LiteralNode(Location.Empty, "10.0", QValueType.Integer);
            var rhs = new LiteralNode(Location.Empty, "5", QValueType.Integer);
            Assert.AreEqual(QValueType.Integer, StatementTypeEvaluator.GetStatementResultType(lhs, rhs));
        }

        [TestMethod]
        public void ExpressionNodeResultTypeDoubleTest()
        {
            var lhs = new LiteralNode(Location.Empty, "10.0", QValueType.Double);
            var rhs = new LiteralNode(Location.Empty, "5.0", QValueType.Double);
            var expression = new ArthimetricExpressionNode(Location.Empty, lhs, ArthimetricOperator.Mult, rhs);
            Assert.AreEqual(QValueType.Double, expression.GetQValueType());
        }

        [TestMethod]
        public void ExpressionNodeResultTypeDoubleAndMoneyTest()
        {
            var lhs = new LiteralNode(Location.Empty, "10.0", QValueType.Money);
            var rhs = new LiteralNode(Location.Empty, "5.0", QValueType.Double);
            var expression = new ArthimetricExpressionNode(Location.Empty, lhs, ArthimetricOperator.Mult, rhs);
            Assert.AreEqual(QValueType.Money, expression.GetQValueType());
        }

        [TestMethod]
        public void ExpressionNodeResultTypeIntAndMoneyTest()
        {
            var lhs = new LiteralNode(Location.Empty, "10.0", QValueType.Money);
            var rhs = new LiteralNode(Location.Empty, "5.0", QValueType.Integer);
            var expression = new ArthimetricExpressionNode(Location.Empty, lhs, ArthimetricOperator.Mult, rhs);
            Assert.AreEqual(QValueType.Money, expression.GetQValueType());
        }

        [TestMethod]
        public void ExpressionNodeResultTypeDoubleAndIntTest()
        {
            var lhs = new LiteralNode(Location.Empty, "10.0", QValueType.Double);
            var rhs = new LiteralNode(Location.Empty, "5.0", QValueType.Integer);
            var expression = new ArthimetricExpressionNode(Location.Empty, lhs, ArthimetricOperator.Mult, rhs);
            Assert.AreEqual(QValueType.Double, expression.GetQValueType());
        }
    }
}
