using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.Analysis;
using QLParser.AST.Nodes;

namespace QLParser.Tests.AST.Validators
{
    [TestClass]
    public class ASTValidatorTests : QLTest
    {
        private Node _validAST;
        private Node _invalidAST;

        [TestInitialize]
        public void Initialize()
        {
            _validAST = new FormNode("ValidForm");
            var firstQuestion = new QuestionNode("Q1", "Do you like puppies?", QValueType.BOOLEAN);
            var secondQuestion = new QuestionNode("Q2", "Do you like kittens?", QValueType.BOOLEAN);

            _validAST.AddNode(firstQuestion);
            _validAST.AddNode(secondQuestion);

            _invalidAST = new FormNode("InvalidForm");
            _invalidAST.AddNode(new FormNode("InvalidSecondForm"));
        }

        [TestMethod]
        public void NoErrorsForValidAST()
        {
            Analyser.Analyse(_validAST);
            var errors = Analyser.GetErrors();

            Assert.AreEqual(0, errors.Count);
        }

        [TestMethod]
        public void ErrorForInvalidAST()
        {
            Analyser.Analyse(_invalidAST);
            var errors = Analyser.GetErrors();
            Assert.AreEqual(1, errors.Count);
        }

        [TestMethod]
        public void MultipleFormNodesInAST()
        {
            Analyser.Analyse(_invalidAST);
            var errors = Analyser.GetErrors();
            Assert.AreEqual("ERROR This AST contains multiple 'FormNode'.", errors[0]);
        }

        [TestMethod]
        public void NoErrorsWhenLogErrorsIsFalse()
        {
            Analyser.Analyse(_validAST);
            var errors = Analyser.GetErrors();
            Assert.AreEqual(0, errors.Count);

        }
    }
}