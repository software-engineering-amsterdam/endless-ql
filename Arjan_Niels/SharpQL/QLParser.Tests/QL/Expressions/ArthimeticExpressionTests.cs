using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.AST.QL;
using QLParser.AST.QL.ExpressionNodes;
using QLParser.AST.QL.ExpressionNodes.Enums;
using System.Linq;

namespace QLParser.Tests.QL.Expressions
{
    [TestClass]
    public class ArthimetricExpressionTests : QLTest
    {
        private readonly string _simpleComparionWithNumbersFormRaw = "form testForm {" +
            "   if  sellingPrice < 10 + 50 * 20 { }" +
            "}";

        private readonly string _simpleComparionWithNumbersAndParensFormRaw = "form testForm {" +
            "   if  sellingPrice < (10 + 50) * 20 { }" +
            "}";

        private readonly string _simpleComparionWithNumbersAndVarsFormRaw = "form testForm {" +
            "   if  sellingPrice < sellingPrice * 20 { }" +
            "}";

        [TestMethod]
        public void MultiplyBeforePlusTest()
        {
            FormNode form = QLParserHelper.Parse(_simpleComparionWithNumbersFormRaw);
            var comparisonNode = form.Children
                .Where(x => x.Type == NodeType.Conditional)
                .Select(x => x as ConditionalNode)
                .First().Expression as ComparisonExpressionNode;

            var arthimetric = comparisonNode.Right as ArthimetricExpressionNode;
            Assert.AreEqual(NodeType.Literal, arthimetric.Left.GetNodeType());
            Assert.AreEqual(ArthimetricOperator.Plus, arthimetric.Operator);
            Assert.AreEqual(NodeType.ArthimetricExpression, arthimetric.Right.GetNodeType());
            Assert.AreEqual(QValueType.Integer, arthimetric.Right.GetQValueType());
        }

        [TestMethod]
        public void ParenthesesTest()
        {
            FormNode form = QLParserHelper.Parse(_simpleComparionWithNumbersAndParensFormRaw);
            var comparisonNode = form.Children
                .Where(x => x.Type == NodeType.Conditional)
                .Select(x => x as ConditionalNode)
                .First().Expression as ComparisonExpressionNode;

            var arthimetric = comparisonNode.Right as ArthimetricExpressionNode;
            Assert.AreEqual(NodeType.ArthimetricExpression, arthimetric.Left.GetNodeType());
            Assert.AreEqual(ArthimetricOperator.Mult, arthimetric.Operator);
            Assert.AreEqual(NodeType.Literal, arthimetric.Right.GetNodeType());
        }

        [TestMethod]
        public void MultiplicationsWithVarsTest()
        {
            FormNode form = QLParserHelper.Parse(_simpleComparionWithNumbersAndVarsFormRaw);
            var comparisonNode = form.Children
                .Where(x => x.Type == NodeType.Conditional)
                .Select(x => x as ConditionalNode)
                .First().Expression as ComparisonExpressionNode;

            var arthimetric = comparisonNode.Right as ArthimetricExpressionNode;
            Assert.AreEqual(NodeType.Identifier, arthimetric.Left.GetNodeType());
            Assert.AreEqual(ArthimetricOperator.Mult, arthimetric.Operator);
            Assert.AreEqual(NodeType.Literal, arthimetric.Right.GetNodeType());
        }

        [TestMethod]
        public void ParseArthimetricExpressionMultTest()
        {
            var result = ArthimetricExpressionNode.ParseArthimeticOperator("*");
            Assert.AreEqual(ArthimetricOperator.Mult, result);
        }

        [TestMethod]
        public void ParseArthimetricExpressionDivTest()
        {
            var result = ArthimetricExpressionNode.ParseArthimeticOperator("/");
            Assert.AreEqual(ArthimetricOperator.Div, result);
        }

        [TestMethod]
        public void ParseArthimetricExpressionPlusTest()
        {
            var result = ArthimetricExpressionNode.ParseArthimeticOperator("+");
            Assert.AreEqual(ArthimetricOperator.Plus, result);
        }

        [TestMethod]
        public void ParseArthimetricExpressionMinusTest()
        {
            var result = ArthimetricExpressionNode.ParseArthimeticOperator("-");
            Assert.AreEqual(ArthimetricOperator.Minus, result);
        }
    }
}