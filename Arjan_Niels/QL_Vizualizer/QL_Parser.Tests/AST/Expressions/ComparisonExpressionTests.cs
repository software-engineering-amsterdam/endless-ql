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
                .Where(x => x.Type == NodeType.COMPARISON_EXPRESSION)
                .Select(x => x as ComparisonExpressionNode)
                .First();

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
