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
            var firstQuestion = new QuestionNode(NodeType.QUESTION, "Q1", "Do you like puppies?");
            var secondQuestion = new QuestionNode(NodeType.QUESTION, "Q2", "Do you like kittens?");
            var thirdQuestion = new QuestionNode(NodeType.QUESTION, "Q3", "Is this the first question?");
            thirdQuestion.AddNode(new FormNode("InvalidFormInLowerLayer"));
            var forthQuestion = new QuestionNode(NodeType.QUESTION, "Q4", "Is this the forthQuestion?");

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
