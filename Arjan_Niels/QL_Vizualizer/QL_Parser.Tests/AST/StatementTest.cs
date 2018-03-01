using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;
using QL_Parser.AST.Nodes.ExpressionNodes;
using System.Linq;

namespace QL_Parser.Tests.AST
{
    [TestClass]
    public class StatementTest : QLTest
    {
        private FormNode _simpleForm;
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
        }

        [TestMethod]
        public void SingleVariableNameTest()
        {
            var statement = _simpleForm.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as ValueNode;

            Assert.AreEqual("soldAHouse", statement.ID);
        }

        [TestMethod]
        public void ComplexVariableTest()
        {
            var statement = _complexeForm.Children
                .Where(x => x.Type == NodeType.CONDITIONAL)
                .Select(x => x as ConditionalNode)
                .First().Expression as LogicalExpressionNode;

            var leftSide = (ValueNode)statement.Left;
            var rightSide = (ValueNode)statement.Right;

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
            var leftLeftValue = (ValueNode)leftSide.Left;
            var leftRightValue = (ValueNode)leftSide.Right;

            Assert.AreEqual("firstArgument", leftLeftValue.ID);
            Assert.AreEqual(LogicalOperator.OR, leftSide.Operator);
            Assert.AreEqual("secondArgument", leftRightValue.ID);

            // rhs
            var rightLeftValue = (ValueNode)rightSide.Left;
            var rightRightValue = (ValueNode)rightSide.Right;
            Assert.AreEqual("thirdArgument", rightLeftValue.ID);
            Assert.AreEqual(LogicalOperator.OR, rightSide.Operator);
            Assert.AreEqual("forthArgument", rightRightValue.ID);
        }
    }
}
