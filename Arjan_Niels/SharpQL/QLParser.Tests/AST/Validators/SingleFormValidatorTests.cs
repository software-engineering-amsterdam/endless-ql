using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.Analysis.Syntactic;
using QLParser.AST;
using QLParser.AST.Nodes;

namespace QLParser.Tests.AST
{
    [TestClass]
    public class SingleFormValidatorTest : QLTest
    {
        private Node _validAST;
        private Node _multipleFormAST;
        private Node _multipleFormInLowerNodeAST;
        private Node _multipleLayerValidForm;

        [TestInitialize]
        public void Initialize()
        {
            var firstQuestion = new QuestionNode(new Location(0, 0), "Q1", "Do you like puppies?", QValueType.BOOLEAN);
            var secondQuestion = new QuestionNode(new Location(0, 0), "Q2", "Do you like kittens?", QValueType.BOOLEAN);
            var thirdQuestion = new QuestionNode(new Location(0, 0), "Q3", "Is this the first question?", QValueType.BOOLEAN);
            thirdQuestion.AddNode(new FormNode(new Location(0, 0), "InvalidFormInLowerLayer"));
            var forthQuestion = new QuestionNode(new Location(0, 0), "Q4", "Is this the forthQuestion?", QValueType.BOOLEAN);

            _validAST = new FormNode(new Location(0, 0), "TestForm");
            _validAST.AddNode(firstQuestion);
            _validAST.AddNode(secondQuestion);

            _multipleFormAST = new FormNode(new Location(0, 0), "TestForm");
            _multipleFormAST.AddNode(new FormNode(new Location(0, 0), "InvalidForm"));

            _multipleFormInLowerNodeAST = new FormNode(new Location(0, 0), "InvalidForm");
            _multipleFormInLowerNodeAST.AddNode(thirdQuestion);

            _multipleLayerValidForm = new FormNode(new Location(0, 0), "ValidForm");
            _multipleLayerValidForm.AddNode(forthQuestion);
            forthQuestion.AddNode(firstQuestion);
            forthQuestion.AddNode(secondQuestion);
        }

        [TestMethod]
        public void RootIsOnlyFormNodeTest()
        {
            var validator = new SingleFormValidator();
            Assert.IsTrue(validator.Analyse(_validAST));
        }

        [TestMethod]
        public void RootIsOnlyFormNodeFalseTest()
        {
            var validator = new SingleFormValidator();
            Assert.IsFalse(validator.Analyse(_multipleFormAST));
        }

        [TestMethod]
        public void RootIsOnlyFormNodeInLowerLayerFalseTest()
        {
            var validator = new SingleFormValidator();
            Assert.IsFalse(validator.Analyse(_multipleFormInLowerNodeAST));
        }

        [TestMethod]
        public void MultipleLayerValidForm()
        {
            var validator = new SingleFormValidator();
            Assert.IsTrue(validator.Analyse(_multipleLayerValidForm));
        }
    }
}