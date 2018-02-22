using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.Analysis;
using QL_Parser.Analysis.Semantic;
using QL_Parser.AST.Nodes;

namespace QL_Parser.Tests.Analysis.Semantic
{
    [TestClass]
    public class VariableAnalyserTest
    {
        private FormNode _validForm;
        private readonly string _validFormRaw = "form ValidForm {" +
            "   \"Have you bought a house?\"" +
            "       boughtAHouse: boolean" +
            "}";

        private FormNode _invalidForm;
        private readonly string _invalidFormRaw = "form ValidForm {" +
            "   \"Have you bought a house?\"" +
            "       boughtAHouse: boolean" +

            "   \"Have you bought a house?\"" +
            "       boughtAHouse: boolean" +
            "}";

        [TestInitialize]
        public void Initialize()
        {
            _validForm = QLParserHelper.Parse(_validFormRaw);
            _invalidForm = QLParserHelper.Parse(_invalidFormRaw);
        }

        [TestCleanup]
        public void CleanUp()
        {
            SymbolTable.Reset();
            Analyser.Reset();
        }

        [TestMethod]
        public void DuplicateVariableTest()
        {
            var analyser = new VariableAnalyser();
            var hasSucceded = analyser.Analyse(_invalidForm);

            Assert.AreEqual(1, Analyser.GetErrors().Count);
            Assert.AreEqual("ERROR Duplicate identifier: boughtAHouse BOOLEAN", Analyser.GetErrors()[0]);
        }
    }
}
