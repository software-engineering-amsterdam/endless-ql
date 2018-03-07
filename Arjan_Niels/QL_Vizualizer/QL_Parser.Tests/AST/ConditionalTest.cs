using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;
using System.Linq;

namespace QL_Parser.Tests.AST
{
    [TestClass]
    public class ConditionalTest : QLTest
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
        public void SimpleConditionalNotNullTest()
        {
            var conditionalNode = node.Children
                .Where(x => x.GetType() == typeof(ConditionalNode))
                .Select(x => x as ConditionalNode)
                .First();

            Assert.IsNotNull(conditionalNode);
        }

        [TestMethod]
        public void SingleConditionChildrenCountTest()
        {
            var conditionalNode = node.Children
                .Where(x => x.GetType() == typeof(ConditionalNode))
                .Select(x => x as ConditionalNode)
                .First();

            Assert.AreEqual(1, conditionalNode.Children.Count);
        }
    }
}