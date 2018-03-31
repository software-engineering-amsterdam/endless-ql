using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser;
using QLParser.Analysis;

namespace QL_Parser.Tests.QL.Analysis
{
    [TestClass]
    public class TwoTimesTheSameQLTest
    {
        private const string simpleQLRaw = "form simpleForm{ " +
          "\"Hallo wat is je naam?\"" +
           " name: text" +
          "\"Hallo wat wil je?\"" +
            "wat: text" +
           " }";

        [TestMethod]
        public void TwoTimesFailedTest()
        {
            var ql = QLParserHelper.Parse(simpleQLRaw);
            var result1 = Analyser.Analyse(ql);
            Assert.IsTrue(result1);
            Assert.AreEqual(0, Analyser.GetErrors().Count);

            var ql2 = QLParserHelper.Parse(simpleQLRaw);
            var result2 = Analyser.Analyse(ql2);
            Assert.AreEqual(0, Analyser.GetErrors().Count);
            Assert.IsTrue(result2);
        }
    }
}
