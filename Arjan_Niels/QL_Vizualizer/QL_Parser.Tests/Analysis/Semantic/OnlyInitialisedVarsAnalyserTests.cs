using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.Analysis;
using QL_Parser.Analysis.Semantic;
using QL_Parser.AST.Nodes;

namespace QL_Parser.Tests.Analysis.Semantic
{
    [TestClass]
    public class OnlyInitialisedVarsAnalyserTests : QLTest
    {
        private FormNode _simpleFormWithConditional;
        private FormNode _simpleFormWithNotInitialisedVar;

        [TestInitialize]
        public void Initialize()
        {
            _simpleFormWithConditional = QLParserHelper.Parse(FormExamples.SimpleFormWithConditional);
            _simpleFormWithNotInitialisedVar = QLParserHelper.Parse(FormExamples.SimpleFormWithConditionalNotInitialised);
        }

        [TestMethod]
        public void AllVarsInitialised()
        {
            var variableAnalyser = new VariableAnalyser();
            variableAnalyser.Analyse(_simpleFormWithConditional);

            var onlyIdentifiedVarsAnalyser = new OnlyInitialisedVarsAnalyser();
            var result = onlyIdentifiedVarsAnalyser.Analyse(_simpleFormWithConditional);

            Assert.IsTrue(result);
            Assert.AreEqual(0, Analyser.GetErrors().Count);
        }

        [TestMethod]
        public void NotAllVarsInitialised()
        {
            var variableAnalyser = new VariableAnalyser();
            variableAnalyser.Analyse(_simpleFormWithNotInitialisedVar);

            var onlyIdentifiedVarsAnalyser = new OnlyInitialisedVarsAnalyser();
            var result = onlyIdentifiedVarsAnalyser.Analyse(_simpleFormWithNotInitialisedVar);

            Assert.IsFalse(result);
            Assert.AreEqual(1, Analyser.GetErrors().Count);
            Assert.AreEqual("ERROR Unknown identifier notInitialisedVar in statement", Analyser.GetErrors()[0]);

        }
    }
}
