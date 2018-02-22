using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;
using System.Linq;

namespace QL_Parser.Tests.AST
{
    [TestClass]
    public class StatementTest
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
                .Where(x => x.GetType() == typeof(ConditionalNode))
                .Select(x => x as ConditionalNode)
                .First().StatementNode;

            Assert.AreEqual("soldAHouse", statement.ID);
            Assert.IsNull(statement.LeftSide);
            Assert.IsNull(statement.Operator);
            Assert.IsNull(statement.RightSide);
        }

        [TestMethod]
        public void ComplexVariableTest()
        {
            var statement = _complexeForm.Children
                .Where(x => x.GetType() == typeof(ConditionalNode))
                .Select(x => x as ConditionalNode)
                .First().StatementNode;

            Assert.AreEqual("soldAHouse", statement.LeftSide.ID);
            Assert.AreEqual("&&", statement.Operator);
            Assert.AreEqual("hasSeenHouseOfCards", statement.RightSide.ID);
            Assert.IsNull(statement.ID);
        }

        [TestMethod]
        public void ComplexNestedStatementFormTest()
        {
            var statement = _nestedStatementForm.Children
                .Where(x => x.GetType() == typeof(ConditionalNode))
                .Select(x => x as ConditionalNode)
                .First().StatementNode;

            var lhs = statement.LeftSide;
            var rhs = statement.RightSide;
            Assert.AreEqual("&&", statement.Operator);

            // lhs
            Assert.AreEqual("firstArgument", lhs.LeftSide.ID);
            Assert.AreEqual("||", lhs.Operator);
            Assert.AreEqual("secondArgument", lhs.RightSide.ID);

            // rhs
            Assert.AreEqual("thirdArgument", rhs.LeftSide.ID);
            Assert.AreEqual("||", rhs.Operator);
            Assert.AreEqual("forthArgument", rhs.RightSide.ID);
        }
    }
}
