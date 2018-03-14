using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.Analysis;
using QLParser.Analysis.Semantic;
using QLParser.AST.Nodes;

namespace QLParser.Tests.Analysis.Semantic
{
    [TestClass]
    public class OnlyInitialisedVarsAnalyserTests : QLTest
    {
        private FormNode _simpleFormWithConditional;
        private FormNode _simpleFormWithNotInitialisedVar;
        private FormNode _simpleFormWithComputedNode;

        [TestInitialize]
        public void Initialize()
        {
            _simpleFormWithConditional = QLParserHelper.Parse(FormExamples.SimpleFormWithConditional);
            _simpleFormWithNotInitialisedVar = QLParserHelper.Parse(FormExamples.SimpleFormWithConditionalNotInitialised);
            _simpleFormWithComputedNode = QLParserHelper.Parse(FormExamples.SimpleFormWithComputedNode);
        }

        [TestMethod]
        public void AllVarsInitialised()
        {
            var variableAnalyser = new DuplicateVariableAnalyser();
            variableAnalyser.Analyse(_simpleFormWithConditional);

            var onlyIdentifiedVarsAnalyser = new OnlyInitialisedVarsAnalyser();
            var result = onlyIdentifiedVarsAnalyser.Analyse(_simpleFormWithConditional);

            Assert.IsTrue(result);
            Assert.AreEqual(0, Analyser.GetErrors().Count);
        }

        [TestMethod]
        public void NotAllVarsInitialised()
        {
            var variableAnalyser = new DuplicateVariableAnalyser();
            variableAnalyser.Analyse(_simpleFormWithNotInitialisedVar);

            var onlyIdentifiedVarsAnalyser = new OnlyInitialisedVarsAnalyser();
            var result = onlyIdentifiedVarsAnalyser.Analyse(_simpleFormWithNotInitialisedVar);

            Assert.IsFalse(result);
            Assert.AreEqual(1, Analyser.GetErrors().Count);
            Assert.AreEqual("ERROR Unknown identifier 'notInitialisedVar' in statement", Analyser.GetErrors()[0]);
        }

        [TestMethod]
        public void NotAllVarsInitialisedInComputed()
        {
            var variableAnalyser = new DuplicateVariableAnalyser();
            variableAnalyser.Analyse(_simpleFormWithComputedNode);

            var onlyIdentifiedVarsAnalyser = new OnlyInitialisedVarsAnalyser();
            var result = onlyIdentifiedVarsAnalyser.Analyse(_simpleFormWithComputedNode);

            Assert.IsFalse(result);
            Assert.AreEqual(1, Analyser.GetErrors().Count);
            Assert.AreEqual("ERROR Unknown identifier 'notInitialisedVar' in statement", Analyser.GetErrors()[0]);
        }
    }
}