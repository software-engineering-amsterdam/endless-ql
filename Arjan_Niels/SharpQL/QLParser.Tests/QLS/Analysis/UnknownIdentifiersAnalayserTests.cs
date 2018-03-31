using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser;
using QLParser.Analysis;
using QLParser.Analysis.QLS;
using QLParser.AST.QL;
using QLParser.AST.QLS;

namespace QL_Parser.Tests.QLS.Analysis
{
    [TestClass]
    public class UnknownIdentifiersAnalayserTests
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

        private const string simpleWithTypoInQLS = "stylesheet SimpleQLS {" +
            "   page \"First page\" {" +
            "       section \"First section\" {" +
            "           question soldHoIn2010" +
            "       }" +
            "   }" +
            "}";

        [TestMethod]
        public void UnknownIdentifierTests()
        {
            SymbolTable.Add("soldHouseIn2010", QValueType.Boolean);
            QLSNode node = QLSParserHelper.Parse(simpleQLS);
            var analyser = new UnknownIdentifiersAnalyser();
            var isValid = analyser.Analyse(node);

            Assert.IsTrue(isValid);
        }

        [TestMethod]
        public void UnknownIdentifierWithTypoTests()
        {
            SymbolTable.Add("soldHouseIn2010", QValueType.Boolean);
            QLSNode node = QLSParserHelper.Parse(simpleWithTypoInQLS);
            var analyser = new UnknownIdentifiersAnalyser();
            var isValid = analyser.Analyse(node);

            Assert.IsFalse(isValid);
        }
    }
}
