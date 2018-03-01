using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.Analysis;
using QL_Parser.Analysis.Semantic;
using QL_Parser.AST.Nodes;

namespace QL_Parser.Tests.Analysis.Semantic
{
    [TestClass]
    public class DuplicateVariableAnalyserTest : QLTest
    {
        private FormNode _validForm;
        private FormNode _invalidForm;
        private FormNode _duplicateComputedForm;


        [TestInitialize]
        public void Initialize()
        {
            _validForm = QLParserHelper.Parse(FormExamples.SimpleForm);
            _invalidForm = QLParserHelper.Parse(FormExamples.SimpleFormWithDuplicateVars);
            _duplicateComputedForm = QLParserHelper.Parse(FormExamples.DuplicateVarsAndComputedForm);
        }

        [TestMethod]
        public void NoDuplicateVariableTest()
        {
            var analyser = new DuplicateVariableAnalyser();
            var hasSucceded = analyser.Analyse(_validForm);

            Assert.IsTrue(hasSucceded);
            Assert.AreEqual(0, Analyser.GetErrors().Count);

        }

        [TestMethod]
        public void DuplicateVariableTest()
        {
            var analyser = new DuplicateVariableAnalyser();
            var hasSucceded = analyser.Analyse(_invalidForm);

            Assert.IsFalse(hasSucceded);
            Assert.AreEqual(1, Analyser.GetErrors().Count);
            Assert.AreEqual("ERROR Duplicate identifier boughtAHouse BOOLEAN", Analyser.GetErrors()[0]);
        }

        [TestMethod]
        public void DuplicateVariableInComputedTest()
        {
            var analyser = new DuplicateVariableAnalyser();
            var hasSucceded = analyser.Analyse(_duplicateComputedForm);

            Assert.IsFalse(hasSucceded);
            Assert.AreEqual(1, Analyser.GetErrors().Count);
        }
    }
}
