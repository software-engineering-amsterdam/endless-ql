using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using QLParser.AST.QL.ExpressionNodes.Enums;
using System.Linq;

namespace QLParser.Tests.QL.Expressions
{
    [TestClass]
    public class LiteralTests : QLTest
    {
        private readonly string _simpleIntegerForm = "form TestForm {" +
            "   if 10 < 5 {}" +
            "}";
        private readonly string _simpleDoubleForm = "form TestForm {" +
            "   if 10.0 > 5.5 {}" +
            "}";

        [TestMethod]
        public void SimpleIntegerComparisonForTest()
        {
            FormNode form = QLParserHelper.Parse(_simpleIntegerForm);
            var comparisonNode = form.Children
            .Where(x => x.Type == NodeType.CONDITIONAL)
            .Select(x => x as ConditionalNode)
            .First().Expression as ComparisonExpressionNode;

            var left = comparisonNode.Left as LiteralNode;
            var opr = comparisonNode.Operator;
            var right = comparisonNode.Right as LiteralNode;

            Assert.AreEqual("10", left.Value);
            Assert.AreEqual(QValueType.INTEGER, left.QValueType);
            Assert.AreEqual(ComparisonOperator.LessThan, opr);
            Assert.AreEqual("5", right.Value);
            Assert.AreEqual(QValueType.INTEGER, left.QValueType);
        }

        [TestMethod]
        public void SimpleDoubleComparisonFormTest()
        {
            FormNode form = QLParserHelper.Parse(_simpleDoubleForm);
            var comparisonNode = form.Children
            .Where(x => x.Type == NodeType.CONDITIONAL)
            .Select(x => x as ConditionalNode)
            .First().Expression as ComparisonExpressionNode;

            var left = comparisonNode.Left as LiteralNode;
            var opr = comparisonNode.Operator;
            var right = comparisonNode.Right as LiteralNode;

            Assert.AreEqual("10.0", left.Value);
            Assert.AreEqual(QValueType.DOUBLE, left.QValueType);
            Assert.AreEqual(ComparisonOperator.GreaterThan, opr);
            Assert.AreEqual("5.5", right.Value);
            Assert.AreEqual(QValueType.DOUBLE, left.QValueType);
        }
    }
}