using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.Analysis.Syntactic;
using QLParser.AST;
using QLParser.AST.Nodes;

namespace QLParser.Tests.AST.Validators
{
    [TestClass]
    public class QuestionHasNoChildrenTests : QLTest
    {
        private Node _validAST;
        private Node _invalidAST;

        [TestInitialize]
        public void Initialize()
        {
            var firstQuestion = new QuestionNode(new Location(0, 0), "Q1", "Do you like puppies?", QValueType.BOOLEAN);
            var secondQuestion = new QuestionNode(new Location(0, 0), "Q2", "Do you like kittens?", QValueType.BOOLEAN);
            var thirdQuestion = new QuestionNode(new Location(0, 0), "Q3", "Is this the first question?", QValueType.BOOLEAN);

            _validAST = new FormNode(new Location(0, 0), "ValidForm");
            _validAST.AddNode(firstQuestion);
            _validAST.AddNode(secondQuestion);

            _invalidAST = new FormNode(new Location(0, 0), "InvalidForm");
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