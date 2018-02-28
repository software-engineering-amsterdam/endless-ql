using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.Analysis;
using QL_Parser.AST.Nodes;

namespace QL_Parser.Tests
{
    [TestClass]
    public class InvalidTreeTests : QLTest
    {

        [TestMethod]
        public void RandomCharsTest()
        {
            FormNode randomCharForm = QLParserHelper.Parse(FormExamples.RandomChars);
            var isValid = Analyser.Analyse(randomCharForm);

            Assert.IsFalse(isValid);
        }
    }
}