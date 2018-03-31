using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using QLParser.AST.QL.ExpressionNodes.Enums;
using QLParser.Exceptions;
using System.Linq;

namespace QLParser.Tests.QL.Expressions
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
                .Where(x => x.Type == NodeType.Conditional)
                .Select(x => x as ConditionalNode)
                .First().Expression as ComparisonExpressionNode;

            var left = comparisonNode.Left as IdentifierNode;
            var opr = comparisonNode.Operator;
            var right = comparisonNode.Right as IdentifierNode;

            Assert.AreEqual("sellingPrice", left.ID);
            Assert.AreEqual(ComparisonOperator.LessThan, opr);
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
                .Where(x => x.Type == NodeType.Conditional)
                .Select(x => x as ConditionalNode)
                .First().Expression as ComparisonExpressionNode;

            var left = comparisonNode.Left as IdentifierNode;
            var opr = comparisonNode.Operator;
            var right = comparisonNode.Right as ArthimetricExpressionNode;

            Assert.AreEqual("sellingPrice", left.ID);
            Assert.AreEqual(ComparisonOperator.LessThan, opr);

            var arthLeft = right.Left as LiteralNode;
            var arthOpr = right.Operator;
            var arthRight = right.Right as LiteralNode;

            Assert.AreEqual("50", arthLeft.Value);
            Assert.AreEqual(QValueType.Integer, arthLeft.QValueType);
            Assert.AreEqual(ArthimetricOperator.Mult, arthOpr);
            Assert.AreEqual("20", arthRight.Value);
            Assert.AreEqual(QValueType.Integer, arthRight.QValueType);
        }

        #region Operator tests
        [TestMethod]
        public void ParseComparisonOperatorGTTest()
        {
            var result = ComparisonExpressionNode.ParseComparisonOperator(">");
            Assert.AreEqual(ComparisonOperator.GreaterThan, result);
        }

        [TestMethod]
        public void ParseComparisonOperatorGETest()
        {
            var result = ComparisonExpressionNode.ParseComparisonOperator(">=");
            Assert.AreEqual(ComparisonOperator.GreaterEqual, result);
        }

        [TestMethod]
        public void ParseComparisonOperatorLTTest()
        {
            var result = ComparisonExpressionNode.ParseComparisonOperator("<");
            Assert.AreEqual(ComparisonOperator.LessThan, result);
        }

        [TestMethod]
        public void ParseComparisonOperatorLETest()
        {
            var result = ComparisonExpressionNode.ParseComparisonOperator("<=");
            Assert.AreEqual(ComparisonOperator.LessEqual, result);
        }

        [TestMethod]
        public void ParseComparisonOperatorEQTest()
        {
            var result = ComparisonExpressionNode.ParseComparisonOperator("==");
            Assert.AreEqual(ComparisonOperator.Equal, result);
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