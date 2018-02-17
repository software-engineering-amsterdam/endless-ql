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


        [TestInitialize]
        public void Initialize()
        {
            _simpleForm = QLParserHelper.Parse(_simpleFormRaw);
            _complexeForm = QLParserHelper.Parse(_complexFormRaw);
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
    }
}
