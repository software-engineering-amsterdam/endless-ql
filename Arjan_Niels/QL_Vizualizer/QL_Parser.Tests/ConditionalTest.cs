using System.Linq;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.Models;

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
            Form form = QLParserHelper.Parse(_simpleConditional);
            var conditionalBlock = form.Sections
                .Where(x => x.GetType() == typeof(ConditionalBlock))
                .First();

            Assert.IsNotNull(conditionalBlock);
        }
    }
}
