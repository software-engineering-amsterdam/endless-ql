using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser;
using QLParser.Analysis.QLS;
using QLParser.AST.QLS;

namespace QL_Parser.Tests.QLS.Analysis
{
    [TestClass]
    public class DuplicateVariableTests
    {
        private const string simpleWithoutDuplicateQLS = "stylesheet SimpleQLS {" +
            "   page \"First page\" {" +
            "       section \"First section\" {" +
            "           question soldHouseIn2010" +
            "       }" +
            "   }" +
            "}";

        private const string simpleDuplicateQLS = "stylesheet SimpleQLS {" +
            "   page \"First page\" {" +
            "       section \"First section\" {" +
            "           question soldHouseIn2010" +
            "           question soldHouseIn2010" +
            "       }" +
            "   }" +
            "}";

        [TestMethod]
        public void SimpleDuplicateQLSTest()
        {
            QLSNode node = QLSParserHelper.Parse(simpleDuplicateQLS);
            var analyser = new DuplicateIdentifiersAnalyser();
            var result = analyser.Analyse(node);
            Assert.IsFalse(result);
        }

        [TestMethod]
        public void SimpleWithoutDuplicateQLSTest()
        {
            QLSNode node = QLSParserHelper.Parse(simpleWithoutDuplicateQLS);
            var analyser = new DuplicateIdentifiersAnalyser();
            var result = analyser.Analyse(node);
            Assert.IsTrue(result);
        }
    }
}
