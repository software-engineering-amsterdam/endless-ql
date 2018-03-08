using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;
using QL_Parser.AST.Nodes.ExpressionNodes;
using QL_Parser.AST.Nodes.ExpressionNodes.Enums;
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
        private readonly string _invalidFormRaw = "form testForm {" +
           "   if sellingPrice < buyingPrice < sellingPrice { }" +
            "}";
        private readonly string _simpleComparionWithNumbersFormRaw = "form testForm {" +
            "   if sellingPrice < 50 * 20 { }" +
            "}";

        [TestMethod]
        public void SimpleComparisonFormTest()
        {
            FormNode form = QLParserHelper.Parse(_simpleComparisonFormRaw);

            var comparisonNode = form.Children
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

        [TestMethod]
        public void InvalidComparisonTest()
        {
            //TODO: Don't know how to detect this error yet!
            FormNode form = QLParserHelper.Parse(_invalidFormRaw);
            Assert.Fail();
        }

        [TestMethod]
        public void SimpleComparisonWithNumbersTest()
        {
            FormNode form = QLParserHelper.Parse(_simpleComparionWithNumbersFormRaw);
            var comparisonNode = form.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as ComparisonExpressionNode;

            var left = comparisonNode.Left as IdentifierNode;
            var opr = comparisonNode.Operator;
            var right = comparisonNode.Right as ArthimetricExpressionNode;

            Assert.AreEqual("sellingPrice", left.ID);
            Assert.AreEqual(ComparisonOperator.LT, opr);

            var arthLeft = right.Left as LiteralNode;
            var arthOpr = right.Operator;
            var arthRight = right.Right as LiteralNode;

            Assert.AreEqual("50", arthLeft.Value);
            Assert.AreEqual(QValueType.INTEGER, arthLeft.QValueType);
            Assert.AreEqual(ArthimetricOperator.MULT, arthOpr);
            Assert.AreEqual("20", arthRight.Value);
            Assert.AreEqual(QValueType.INTEGER, arthRight.QValueType);
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