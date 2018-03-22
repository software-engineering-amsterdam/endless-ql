using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser;
using QLParser.Analysis;
using QLParser.Analysis.QLS;
using QLParser.AST.QL;
using QLParser.AST.QLS;

namespace QL_Parser.Tests.QLS.Analysis
{
    [TestClass]
    public class AllIdentifiersAreUsedTests
    {
        [TestCleanup]
        public void CleanUp()
        {
            SymbolTable.Reset();
        }

        private const string simpleQLS = "stylesheet SimpleQLS {" +
            "   page \"First page\" {" +
            "       section \"First section\" {" +
            "           question soldHouseIn2010" +
            "       }" +
            "   }" +
            "}";

        private const string simpleWithouIdentifiersQLS = "stylesheet SimpleQLS {" +
            "   page \"First page\" {" +
            "       section \"First section\" {" +
            "       }" +
            "   }" +
            "}";

        [TestMethod]
        public void AllIdentifiersAreUsed()
        {
            SymbolTable.Add("soldHouseIn2010", QValueType.BOOLEAN);
            QLSNode node = QLSParserHelper.Parse(simpleQLS);
            var analyser = new AllIdentifiersAreUsedAnalyser();
            var result = analyser.Analyse(node);

            Assert.IsTrue(result);
        }

        [TestMethod]
        public void NoIdentifiersAreUsed()
        {
            SymbolTable.Add("soldHouseIn2010", QValueType.BOOLEAN);
            QLSNode node = QLSParserHelper.Parse(simpleWithouIdentifiersQLS);
            var analyser = new AllIdentifiersAreUsedAnalyser();
            var result = analyser.Analyse(node);

            Assert.IsFalse(result);
        }
    }
}
