using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.Analysis;
using QLParser.Analysis.QL.Syntactic;
using QLParser.AST;
using QLParser.AST.QL;

namespace QLParser.Tests.QL.Validators
{
    [TestClass]
    public class ASTValidatorTests : QLTest
    {
        private QLCollectionNode _validAST;
        private QLCollectionNode _invalidAST;

        [TestInitialize]
        public void Initialize()
        {
            _validAST = new FormNode(new Location(0, 0), "ValidForm");
            var firstQuestion = new QuestionNode(Location.Empty, "Q1", "Do you like puppies?", QValueType.BOOLEAN);
            var secondQuestion = new QuestionNode(Location.Empty, "Q2", "Do you like kittens?", QValueType.BOOLEAN);

            _validAST.AddNode(firstQuestion);
            _validAST.AddNode(secondQuestion);

            _invalidAST = new FormNode(Location.Empty, "InvalidForm");
            _invalidAST.AddNode(new FormNode(Location.Empty, "InvalidSecondForm"));
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
            var analyser = new SingleFormValidator();
            var result = analyser.Analyse(_invalidAST);
            Assert.IsFalse(result);

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