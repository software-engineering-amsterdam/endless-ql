using System;
using System.Linq;
using NUnit.Framework;
using QuestionnaireUI.Models;

namespace UnitTests.UI.UnitTests
{
    [TestFixture]
    public class ChangeNotificationCollectionTests
    {
        private QuestionnaireModel m_questionnaire;
        private QuestionModel m_inputQuestion1;
        private QuestionModel m_inputQuestion2;

        [SetUp]
        public void Init()
        {
            m_questionnaire = new QuestionnaireModel(
                new Guid("E746E1F1-0A3A-400E-9824-C28427D51CD6"),
                "TestQuestionaire");

            m_inputQuestion1 = new QuestionModel(
                new Guid("40E98F85-949C-48F0-8194-CECEEBD0177F"),
                "int input question",
                true,
                false,
                typeof(int));

            m_inputQuestion2 = new QuestionModel(
                new Guid("9568D4CB-7287-4431-9810-E95A83D050EB"),
                "string input question",
                true,
                false,
                typeof(string));

            m_questionnaire.Questions.Add(m_inputQuestion1);
            m_questionnaire.Questions.Add(m_inputQuestion2);
        }

        [Test]
        public void WhenGivenAModelWithQuestions_ShouldInitializeTheQuestionsCorrectly()
        {
            var wrapper = new QuestionnaireWrapper(m_questionnaire);
            Assert.NotNull(wrapper.Questions);
            CheckModelQuestionsCollectionIsInSync(wrapper);
        }

        [Test]
        public void WhenAddedQuestion_ShouldBeInSync()
        {
            m_questionnaire.Questions.Remove(m_inputQuestion2);
            var wrapper = new QuestionnaireWrapper(m_questionnaire);
            wrapper.Questions.Add(new QuestionWrapper(m_inputQuestion2));
            CheckModelQuestionsCollectionIsInSync(wrapper);
        }
        
        [Test]
        public void WhenRemovedQuestion_ShouldBeInSync()
        {
            var wrapper = new QuestionnaireWrapper(m_questionnaire);
            var questionToRemove = wrapper.Questions.Single(q => q.Model == m_inputQuestion2);
            wrapper.Questions.Remove(questionToRemove);
            CheckModelQuestionsCollectionIsInSync(wrapper);
        }
        private void CheckModelQuestionsCollectionIsInSync(QuestionnaireWrapper wrapper)
        {
            Assert.AreEqual(
                expected: m_questionnaire.Questions.Count,
                actual: wrapper.Questions.Count);
            Assert.IsTrue(m_questionnaire.Questions.All(
                q => wrapper.Questions.Any(wq => wq.Model == q)));
        }
    }
}
