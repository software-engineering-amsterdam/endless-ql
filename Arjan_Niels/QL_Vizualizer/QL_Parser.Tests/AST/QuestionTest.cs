using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Parser.AST.Nodes;

namespace QL_Parser.Tests.AST
{
    [TestClass]
    public class QuestionTest : QLTest
    {
        private readonly string _formRaw = "form simpleForm { " +
            "   \"Have you sold a house in 2010?\"" +
            "       hasSoldAHouse: boolean" +
            "}";

        private readonly string _formMultipleQuestionsRaw = "form moreQuestionsForm { " +
            "   \"Have you sold a house in 2010?\"" +
            "       hasSoldAHouse: boolean" +
            "" +
            "   \"Do you like coffee?\"" +
            "       likesCoffee: boolean" +
            "}";

        #region SimpleForm
        [TestMethod]
        public void CountQuestionsTest()
        {
            var form = QLParserHelper.Parse(_formRaw);
            Assert.AreEqual(1, form.Children.Count);
        }

        [TestMethod]
        public void QuestionTextTest()
        {
            var form = QLParserHelper.Parse(_formRaw);
            var firstQuestion = form.Children[0] as QuestionNode;
            Assert.AreEqual("Have you sold a house in 2010?", firstQuestion.Text);
        }

        [TestMethod]
        public void QuestionIDTest()
        {
            var form = QLParserHelper.Parse(_formRaw);
            var firstQuestion = form.Children[0] as QuestionNode;
            Assert.AreEqual("hasSoldAHouse", firstQuestion.ID);
        }

        [TestMethod]
        public void QuestionTypeTest()
        {
            var form = QLParserHelper.Parse(_formRaw);
            var firstQuestion = form.Children[0] as QuestionNode;
            Assert.AreEqual(QValueType.BOOLEAN, firstQuestion.ValueType);
        }
        #endregion

        #region MulipleQuestionsForm
        [TestMethod]
        public void CountMultipleQuestionsTest()
        {
            var form = QLParserHelper.Parse(_formMultipleQuestionsRaw);
            Assert.AreEqual(2, form.Children.Count);
        }
        #endregion
    }
}