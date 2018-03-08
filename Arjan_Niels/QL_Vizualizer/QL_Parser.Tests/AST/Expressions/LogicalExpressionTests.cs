using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;
using QL_Parser.AST.Nodes.ExpressionNodes;
using QL_Parser.AST.Nodes.ExpressionNodes.Enums;
using System.Linq;

namespace QL_Parser.Tests.AST.Expressions
{
    [TestClass]
    public class LogicalExpressionTests : QLTest
    {
        private FormNode _simpleForm;
        private FormNode _simpleLiteralTrueForm;
        private FormNode _simpleLiteralFalseForm;
        private FormNode _complexLiteralForm;
        private FormNode _complexeForm;
        private FormNode _nestedStatementForm;

        private readonly string _simpleFormRaw = "form SimpleAND {" +
            "\"Have your sold a house last year?\"" +
            "   soldAHouse: boolean" +
            "" +
            "   if soldAHouse {" +
            "       \"For what price did you sell your house?\"" +
            "           pirceHouse: money" +
            "   }" +
            "}";
        private readonly string _simpleLiteralTrueFormRaw = "form SimpleAND {" +
            "\"Have your sold a house last year?\"" +
            "   soldAHouse: boolean" +
            "" +
            "   if true {" +
            "       \"For what price did you sell your house?\"" +
            "           pirceHouse: money" +
            "   }" +
            "}";
        private readonly string _simpleLiteralFalseFormRaw = "form SimpleAND {" +
            "\"Have your sold a house last year?\"" +
            "   soldAHouse: boolean" +
            "" +
            "   if false {" +
            "       \"For what price did you sell your house?\"" +
            "           pirceHouse: money" +
            "   }" +
            "}";
        private readonly string _complexLiteralFormRaw = "form SimpleAND {" +
            "\"Have your sold a house last year?\"" +
            "   soldAHouse: boolean" +
            "" +
            "   if true && false {" +
            "       \"For what price did you sell your house?\"" +
            "           pirceHouse: money" +
            "   }" +
            "}";
        private readonly string _complexFormRaw = "form SimpleAND {" +
            "   \"Have your sold a house last year?\"" +
            "       soldAHouse: boolean" +
            "" +
            "   \"Have you seen House of Cards?\"" +
            "       hasSeenHouseOfCards: boolean" +
            "" +
            "   if soldAHouse && hasSeenHouseOfCards {" +
            "       \"For what price did you sell your house?\"" +
            "           pirceHouse: money" +
            "   }" +
            "}";
        private readonly string _nestedStatementsFormRaw = "form SimpleAND {" +
            "\"Have your sold a house last year?\"" +
            "   soldAHouse: boolean" +
            "" +
            "   if (firstArgument || secondArgument) && (thirdArgument || forthArgument)  {" +
            "       \"For what price did you sell your house?\"" +
            "           pirceHouse: money" +
            "   }" +
            "}";


        [TestInitialize]
        public void Initialize()
        {
            _simpleForm = QLParserHelper.Parse(_simpleFormRaw);
            _complexeForm = QLParserHelper.Parse(_complexFormRaw);
            _nestedStatementForm = QLParserHelper.Parse(_nestedStatementsFormRaw);
            _simpleLiteralTrueForm = QLParserHelper.Parse(_simpleLiteralTrueFormRaw);
            _simpleLiteralFalseForm = QLParserHelper.Parse(_simpleLiteralFalseFormRaw);
            _complexLiteralForm = QLParserHelper.Parse(_complexLiteralFormRaw);
        }

        [TestMethod]
        public void SingleVariableNameTest()
        {
            var statement = _simpleForm.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as IdentifierNode;

            Assert.AreEqual("soldAHouse", statement.ID);
        }

        [TestMethod]
        public void SingleLiteralTrueTest()
        {
            var statement = _simpleLiteralTrueForm.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as LiteralNode;

            Assert.AreEqual("true", statement.Value);
            Assert.AreEqual(QValueType.BOOLEAN, statement.QValueType);
            Assert.AreEqual(QValueType.BOOLEAN, statement.GetQValueType());
        }

        [TestMethod]
        public void SingleLiteralFalseTest()
        {
            var statement = _simpleLiteralFalseForm.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as LiteralNode;

            Assert.AreEqual("false", statement.Value);
            Assert.AreEqual(QValueType.BOOLEAN, statement.QValueType);
            Assert.AreEqual(QValueType.BOOLEAN, statement.GetQValueType());
        }

        [TestMethod]
        public void ComplexLiteralFormTest()
        {
            var statement = _complexLiteralForm.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as LogicalExpressionNode;

            // Both
            Assert.AreEqual(QValueType.BOOLEAN, statement.GetQValueType());

            // Left side
            var left = statement.Left as LiteralNode;
            Assert.AreEqual("true", left.Value);
            Assert.AreEqual(QValueType.BOOLEAN, left.QValueType);

            // Right side
            var right = statement.Right as LiteralNode;
            Assert.AreEqual("false", right.Value);
            Assert.AreEqual(QValueType.BOOLEAN, right.QValueType);
        }

        [TestMethod]
        public void ComplexVariableTest()
        {
            var statement = _complexeForm.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as LogicalExpressionNode;

            var leftSide = (IdentifierNode)statement.Left;
            var rightSide = (IdentifierNode)statement.Right;

            Assert.AreEqual("soldAHouse", leftSide.ID);
            Assert.AreEqual(LogicalOperator.AND, statement.Operator);
            Assert.AreEqual("hasSeenHouseOfCards", rightSide.ID);
        }

        [TestMethod]
        public void ComplexNestedStatementFormTest()
        {
            var statement = _nestedStatementForm.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as LogicalExpressionNode;

            var leftSide = (LogicalExpressionNode)statement.Left;
            var rightSide = (LogicalExpressionNode)statement.Right;
            Assert.AreEqual(LogicalOperator.AND, statement.Operator);

            // lhs
            var leftLeftValue = (IdentifierNode)leftSide.Left;
            var leftRightValue = (IdentifierNode)leftSide.Right;

            Assert.AreEqual("firstArgument", leftLeftValue.ID);
            Assert.AreEqual(LogicalOperator.OR, leftSide.Operator);
            Assert.AreEqual("secondArgument", leftRightValue.ID);

            // rhs
            var rightLeftValue = (IdentifierNode)rightSide.Left;
            var rightRightValue = (IdentifierNode)rightSide.Right;
            Assert.AreEqual("thirdArgument", rightLeftValue.ID);
            Assert.AreEqual(LogicalOperator.OR, rightSide.Operator);
            Assert.AreEqual("forthArgument", rightRightValue.ID);
        }
    }
}