using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;
using System.Linq;

namespace QL_Parser.Tests
{
    [TestClass]
    public class ConditionalTest
    {
        private readonly string _simpleConditional = "form SimpleAND {" +
            "\"Have your sold a house last year?\"" +
            "   soldAHouse: boolean" +
            "" +
            "   if soldAHouse {" +
            "       \"For what price did you sell your house?\"" +
            "           pirceHouse: money" +
            "   }" +
            "}";


        [TestMethod]
        public void SimpleConditionalTokens()
        {
            FormNode form = QLParserHelper.Parse(_simpleConditional);
            var conditionalBlock = form.Children
                .Where(x => x.GetType() == typeof(ConditionalNode))
                .First();

            Assert.IsNotNull(conditionalBlock);
        }
    }
}
