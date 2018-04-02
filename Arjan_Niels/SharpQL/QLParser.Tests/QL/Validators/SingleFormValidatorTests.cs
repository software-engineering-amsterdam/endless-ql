using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.Analysis.QL.Syntactic;
using QLParser.AST;
using QLParser.AST.QL;

namespace QLParser.Tests.QL
{
    [TestClass]
    public class SingleFormValidatorTest : QLTest
    {
        private FormNode _validAST;
        private FormNode _multipleFormAST;
        private FormNode _multipleFormInLowerNodeAST;

        [TestInitialize]
        public void Initialize()
        {
            var firstQuestion = new QuestionNode(Location.Empty, "Q1", "Do you like puppies?", QValueType.Boolean);
            var secondQuestion = new QuestionNode(Location.Empty, "Q2", "Do you like kittens?", QValueType.Boolean);
            var thirdQuestion = new ConditionalNode(Location.Empty, null);
            thirdQuestion.AddNode(new FormNode(Location.Empty, "InvalidFormInLowerLayer"));
            var forthQuestion = new QuestionNode(Location.Empty, "Q4", "Is this the forthQuestion?", QValueType.Boolean);

            _validAST = new FormNode(Location.Empty, "TestForm");
            _validAST.AddNode(firstQuestion);
            _validAST.AddNode(secondQuestion);

            _multipleFormAST = new FormNode(Location.Empty, "TestForm");
            _multipleFormAST.AddNode(new FormNode(Location.Empty, "InvalidForm"));

            _multipleFormInLowerNodeAST = new FormNode(Location.Empty, "InvalidForm");
            _multipleFormInLowerNodeAST.AddNode(thirdQuestion);
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
    }
}