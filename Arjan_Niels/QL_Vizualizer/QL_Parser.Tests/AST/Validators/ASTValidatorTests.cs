using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;
using QL_Parser.AST.Validators;

namespace QL_Parser.Tests.AST.Validators
{
    [TestClass]
    public class ASTValidatorTests
    {
        private Node _validAST;
        private Node _invalidAST;

        [TestInitialize]
        public void Initialize()
        {
            _validAST = new FormNode("ValidForm");
            var firstQuestion = new QuestionNode("Q1", "Do you like puppies?", QuestionType.BOOLEAN);
            var secondQuestion = new QuestionNode("Q2", "Do you like kittens?", QuestionType.BOOLEAN);

            _validAST.AddNode(firstQuestion);
            _validAST.AddNode(secondQuestion);

            _invalidAST = new FormNode("InvalidForm");
            _invalidAST.AddNode(new FormNode("InvalidSecondForm"));
        }

        [TestMethod]
        public void NoErrorsForValidAST()
        {
            ASTValidator.IsValid(_validAST);
            var errors = ASTValidator.GetErrors();

            Assert.AreEqual(0, errors.Count);
        }

        [TestMethod]
        public void ErrorForInvalidAST()
        {
            ASTValidator.IsValid(_invalidAST);
            var errors = ASTValidator.GetErrors();
            Assert.AreEqual(1, errors.Count);
        }

        [TestMethod]
        public void MultipleFormNodesInAST()
        {
            ASTValidator.IsValid(_invalidAST);
            var errors = ASTValidator.GetErrors();
            Assert.AreEqual("This AST contains multiple 'FormNode'.", errors[0]);
        }

        [TestMethod]
        public void NoErrorsWhenLogErrorsIsFalse()
        {
            ASTValidator.IsValid(_invalidAST, logErrors: false);
            var errors = ASTValidator.GetErrors();
            Assert.AreEqual(0, errors.Count);

        }
    }
}
