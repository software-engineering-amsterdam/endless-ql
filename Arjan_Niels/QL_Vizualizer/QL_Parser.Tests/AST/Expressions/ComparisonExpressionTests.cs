using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;
using QL_Parser.AST.Nodes.ExpressionNodes;
using QL_Parser.Exceptions;
using System.Linq;

namespace QL_Parser.Tests.AST.Expressions
{
    [TestClass]
    public class ComparisonExpressionTests : QLTest
    {
        private readonly string _simpleComparisonFormRaw = "form testForm {" +
            "   if sellingPrice < buyingPrice { }" +
            "}";

        [TestMethod]
        public void SimpleComparisonFormTest()
        {
            FormNode node = QLParserHelper.Parse(_simpleComparisonFormRaw);

            var comparisonNode = node.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as ComparisonExpressionNode;

            var left = comparisonNode.Left as IdentifierNode;
            var opr = comparisonNode.Operator;
            var right = comparisonNode.Right as IdentifierNode;

            Assert.AreEqual("sellingPrice", left.ID);
            Assert.AreEqual(ComparisonOperator.LT, opr);
            Assert.AreEqual("buyingPrice", right.ID);
        }


        #region Operator tests
        [TestMethod]
        public void ParseComparisonOperatorGTTest()
        {
            var result = ComparisonExpressionNode.ParseComparisonOperator(">");
            Assert.AreEqual(ComparisonOperator.GT, result);
        }

        [TestMethod]
        public void ParseComparisonOperatorGETest()
        {
            var result = ComparisonExpressionNode.ParseComparisonOperator(">=");
            Assert.AreEqual(ComparisonOperator.GE, result);
        }

        [TestMethod]
        public void ParseComparisonOperatorLTTest()
        {
            var result = ComparisonExpressionNode.ParseComparisonOperator("<");
            Assert.AreEqual(ComparisonOperator.LT, result);
        }

        [TestMethod]
        public void ParseComparisonOperatorLETest()
        {
            var result = ComparisonExpressionNode.ParseComparisonOperator("<=");
            Assert.AreEqual(ComparisonOperator.LE, result);
        }

        [TestMethod]
        public void ParseComparisonOperatorEQTest()
        {
            var result = ComparisonExpressionNode.ParseComparisonOperator("==");
            Assert.AreEqual(ComparisonOperator.EQ, result);
        }

        [TestMethod]
        [ExpectedException(typeof(UnknownOperatorException))]
        public void ParseComparisonOperatorInvalidInput()
        {
            ComparisonExpressionNode.ParseComparisonOperator("-");
        }
        #endregion
    }
}
