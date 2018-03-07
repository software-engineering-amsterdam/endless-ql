using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.Analysis.Syntactic;
using QL_Parser.AST.Nodes;

namespace QL_Parser.Tests.AST.Validators
{
    [TestClass]
    public class QuestionHasNoChildrenTests : QLTest
    {
        private Node _validAST;
        private Node _invalidAST;

        [TestInitialize]
        public void Initialize()
        {
            var firstQuestion = new QuestionNode("Q1", "Do you like puppies?", QValueType.BOOLEAN);
            var secondQuestion = new QuestionNode("Q2", "Do you like kittens?", QValueType.BOOLEAN);
            var thirdQuestion = new QuestionNode("Q3", "Is this the first question?", QValueType.BOOLEAN);

            _validAST = new FormNode("ValidForm");
            _validAST.AddNode(firstQuestion);
            _validAST.AddNode(secondQuestion);

            _invalidAST = new FormNode("InvalidForm");
            _invalidAST.AddNode(firstQuestion);
            _invalidAST.AddNode(thirdQuestion);
            thirdQuestion.AddNode(secondQuestion);
        }

        [TestMethod]
        public void QuestionHasNoChildrenTest()
        {
            var validator = new QuestionHasNoChildrenValidator();
            Assert.IsTrue(validator.Analyse(_validAST));
        }

        [TestMethod]
        public void QuestionHasChildrenTest()
        {
            var validator = new QuestionHasNoChildrenValidator();
            Assert.IsFalse(validator.Analyse(_invalidAST));
        }
    }
}