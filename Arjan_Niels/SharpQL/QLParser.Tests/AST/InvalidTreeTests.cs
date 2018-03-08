using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.Analysis;
using QLParser.AST.Nodes;

namespace QLParser.Tests
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