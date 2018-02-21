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
            Assert.IsNull(statement.lhs);
            Assert.IsNull(statement.opr);
            Assert.IsNull(statement.rhs);
        }

        [TestMethod]
        public void ComplexVariableTest()
        {
            var statement = _complexeForm.Children
                .Where(x => x.GetType() == typeof(ConditionalNode))
                .Select(x => x as ConditionalNode)
                .First().StatementNode;

            Assert.AreEqual("soldAHouse", statement.lhs.ID);
            Assert.AreEqual("&&", statement.opr);
            Assert.AreEqual("hasSeenHouseOfCards", statement.rhs.ID);
            Assert.IsNull(statement.ID);
        }

        [TestMethod]
        public void ComplexNestedStatementFormTest()
        {
            var statement = _nestedStatementForm.Children
                .Where(x => x.GetType() == typeof(ConditionalNode))
                .Select(x => x as ConditionalNode)
                .First().StatementNode;

            var lhs = statement.lhs;
            var rhs = statement.rhs;
            Assert.AreEqual("&&", statement.opr);

            // lhs
            Assert.AreEqual("firstArgument", lhs.lhs.ID);
            Assert.AreEqual("||", lhs.opr);
            Assert.AreEqual("secondArgument", lhs.rhs.ID);

            // rhs
            Assert.AreEqual("thirdArgument", rhs.lhs.ID);
            Assert.AreEqual("||", rhs.opr);
            Assert.AreEqual("forthArgument", rhs.rhs.ID);
        }
    }
}
