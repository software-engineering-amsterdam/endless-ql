using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.AST.Nodes;
using QLParser.AST.Nodes.ExpressionNodes;
using QLParser.AST.Nodes.ExpressionNodes.Enums;
using System.Linq;

namespace QLParser.Tests.AST.Expressions
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
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as ComparisonExpressionNode;

            var arthimetric = comparisonNode.Right as ArthimetricExpressionNode;
            Assert.AreEqual(NodeType.LITERAL, arthimetric.Left.GetNodeType());
            Assert.AreEqual(ArthimetricOperator.PLUS, arthimetric.Operator);
            Assert.AreEqual(NodeType.ARTHIMETIC_EXPRESSION, arthimetric.Right.GetNodeType());
            Assert.AreEqual(QValueType.INTEGER, arthimetric.Right.GetQValueType());
        }

        [TestMethod]
        public void ParenthesesTest()
        {
            FormNode form = QLParserHelper.Parse(_simpleComparionWithNumbersAndParensFormRaw);
            var comparisonNode = form.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as ComparisonExpressionNode;

            var arthimetric = comparisonNode.Right as ArthimetricExpressionNode;
            Assert.AreEqual(NodeType.ARTHIMETIC_EXPRESSION, arthimetric.Left.GetNodeType());
            Assert.AreEqual(ArthimetricOperator.MULT, arthimetric.Operator);
            Assert.AreEqual(NodeType.LITERAL, arthimetric.Right.GetNodeType());
        }

        [TestMethod]
        public void MultiplicationsWithVarsTest()
        {
            FormNode form = QLParserHelper.Parse(_simpleComparionWithNumbersAndVarsFormRaw);
            var comparisonNode = form.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as ComparisonExpressionNode;

            var arthimetric = comparisonNode.Right as ArthimetricExpressionNode;
            Assert.AreEqual(NodeType.IDENTIFIER, arthimetric.Left.GetNodeType());
            Assert.AreEqual(ArthimetricOperator.MULT, arthimetric.Operator);
            Assert.AreEqual(NodeType.LITERAL, arthimetric.Right.GetNodeType());
        }

        [TestMethod]
        public void ParseArthimetricExpressionMultTest()
        {
            var result = ArthimetricExpressionNode.ParseArthimeticOperator("*");
            Assert.AreEqual(ArthimetricOperator.MULT, result);
        }

        [TestMethod]
        public void ParseArthimetricExpressionDivTest()
        {
            var result = ArthimetricExpressionNode.ParseArthimeticOperator("/");
            Assert.AreEqual(ArthimetricOperator.DIV, result);
        }

        [TestMethod]
        public void ParseArthimetricExpressionPlusTest()
        {
            var result = ArthimetricExpressionNode.ParseArthimeticOperator("+");
            Assert.AreEqual(ArthimetricOperator.PLUS, result);
        }

        [TestMethod]
        public void ParseArthimetricExpressionMinusTest()
        {
            var result = ArthimetricExpressionNode.ParseArthimeticOperator("-");
            Assert.AreEqual(ArthimetricOperator.MINUS, result);
        }
    }
}