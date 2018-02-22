using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;
using QL_Parser.AST.Validators;

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
            var firstQuestion = new QuestionNode("Q1", "Do you like puppies?", QuestionType.BOOLEAN);
            var secondQuestion = new QuestionNode("Q2", "Do you like kittens?", QuestionType.BOOLEAN);
            var thirdQuestion = new QuestionNode("Q3", "Is this the first question?", QuestionType.BOOLEAN);
            thirdQuestion.AddNode(new FormNode("InvalidFormInLowerLayer"));
            var forthQuestion = new QuestionNode("Q4", "Is this the forthQuestion?", QuestionType.BOOLEAN);

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

        [TestMethod]
        public void RootIsOnlyFormNodeTest()
        {
            IASTValidator validator = new SingleFormValidator();
            Assert.IsTrue(validator.IsValid(_validAST, logErrors: false));
        }

        [TestMethod]
        public void RootIsOnlyFormNodeFalseTest()
        {
            IASTValidator validator = new SingleFormValidator();
            Assert.IsFalse(validator.IsValid(_multipleFormAST, logErrors: false));
        }

        [TestMethod]
        public void RootIsOnlyFormNodeInLowerLayerFalseTest()
        {
            IASTValidator validator = new SingleFormValidator();
            Assert.IsFalse(validator.IsValid(_multipleFormInLowerNodeAST, logErrors: false));
        }

        [TestMethod]
        public void MultipleLayerValidForm()
        {
            IASTValidator validator = new SingleFormValidator();
            Assert.IsTrue(validator.IsValid(_multipleLayerValidForm, logErrors: false));
        }
    }
}
