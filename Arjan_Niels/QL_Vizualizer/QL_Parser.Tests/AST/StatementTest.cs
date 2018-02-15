using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;
using System.Linq;

namespace QL_Parser.Tests.AST
{
    [TestClass]
    public class StatementTest
    {
        private FormNode node;
        private readonly string _simpleConditional = "form SimpleAND {" +
            "\"Have your sold a house last year?\"" +
            "   soldAHouse: boolean" +
            "" +
            "   if soldAHouse {" +
            "       \"For what price did you sell your house?\"" +
            "           pirceHouse: money" +
            "   }" +
            "}";

        [TestInitialize]
        public void Initialize()
        {
            node = QLParserHelper.Parse(_simpleConditional);
        }

        [TestMethod]
        public void SingleVariableNameTest()
        {
            var statement = node.Children
                .Where(x => x.GetType() == typeof(ConditionalNode))
                .Select(x => x as ConditionalNode)
                .First().StatementNode;

            Assert.AreEqual("soldAHouse", statement.ID);
            Assert.IsNull(statement.lhs);
            Assert.IsNull(statement.opr);
            Assert.IsNull(statement.rhs);
        }
    }
}
