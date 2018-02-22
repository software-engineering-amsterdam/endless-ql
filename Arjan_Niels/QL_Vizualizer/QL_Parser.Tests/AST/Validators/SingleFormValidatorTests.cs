using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.Analysis;
using QL_Parser.Analysis.Syntactic;
using QL_Parser.AST.Nodes;

namespace QL_Parser.Tests.AST
{
    [TestClass]
    public class UnitTest1
    {
        private Node _validAST;
        private Node _multipleFormAST;
        private Node _multipleFormInLowerNodeAST;
        private Node _multipleLayerValidForm;

        [TestInitialize]
        public void Initialize()
        {
            var firstQuestion = new QuestionNode("Q1", "Do you like puppies?", QValueType.BOOLEAN);
            var secondQuestion = new QuestionNode("Q2", "Do you like kittens?", QValueType.BOOLEAN);
            var thirdQuestion = new QuestionNode("Q3", "Is this the first question?", QValueType.BOOLEAN);
            thirdQuestion.AddNode(new FormNode("InvalidFormInLowerLayer"));
            var forthQuestion = new QuestionNode("Q4", "Is this the forthQuestion?", QValueType.BOOLEAN);

            _validAST = new FormNode("TestForm");
            _validAST.AddNode(firstQuestion);
            _validAST.AddNode(secondQuestion);

            _multipleFormAST = new FormNode("TestForm");
            _multipleFormAST.AddNode(new FormNode("InvalidForm"));

            _multipleFormInLowerNodeAST = new FormNode("InvalidForm");
            _multipleFormInLowerNodeAST.AddNode(thirdQuestion);

            _multipleLayerValidForm = new FormNode("ValidForm");
            _multipleLayerValidForm.AddNode(forthQuestion);
            forthQuestion.AddNode(firstQuestion);
            forthQuestion.AddNode(secondQuestion);
        }

        [TestCleanup]
        public void CleanUp()
        {
            Analyser.Reset();
            SymbolTable.Reset();
        }

        [TestMethod]
        public void RootIsOnlyFormNodeTest()
        {
            var validator = new SingleFormValidator();
            Assert.IsTrue(validator.Analyse(_validAST, logErrors: false));
        }

        [TestMethod]
        public void RootIsOnlyFormNodeFalseTest()
        {
            var validator = new SingleFormValidator();
            Assert.IsFalse(validator.Analyse(_multipleFormAST, logErrors: false));
        }

        [TestMethod]
        public void RootIsOnlyFormNodeInLowerLayerFalseTest()
        {
            var validator = new SingleFormValidator();
            Assert.IsFalse(validator.Analyse(_multipleFormInLowerNodeAST, logErrors: false));
        }

        [TestMethod]
        public void MultipleLayerValidForm()
        {
            var validator = new SingleFormValidator();
            Assert.IsTrue(validator.Analyse(_multipleLayerValidForm, logErrors: false));
        }
    }
}
