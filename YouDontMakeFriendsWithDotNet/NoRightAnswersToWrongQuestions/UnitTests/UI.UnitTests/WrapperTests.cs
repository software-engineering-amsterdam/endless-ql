using System;
using NUnit.Framework;
using QuestionnaireUI.Models;

namespace UnitTests.UI.UnitTests
{
    [TestFixture]
    public class WrapperTests
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
        }


        [Test]
        public void WhenQuestionnaireWrapperGivenModel_ShouldbeContainedInModelProperty()
        {
            var wrapper = new QuestionnaireWrapper(m_questionnaire);
            Assert.AreEqual(expected: m_questionnaire, actual: wrapper.Model);
        }
        
        [Test]
        public void WhenQuestionnaireWrapperGivenNullModel_ShouldThrowArgumentNullException()
        {
            var constraint = Is.TypeOf<ArgumentNullException>()
                .And
                .Property(nameof(ArgumentNullException.ParamName))
                .EqualTo("model");

            Assert.Throws(constraint, () => new QuestionnaireWrapper(null));
        }

        [Test]
        public void WhenQuestionnaireWrapperHasModel_ShouldGetUnderlyingModelValues()
        {
            m_questionnaire.Questions.Add(m_inputQuestion1);
            var wrapper = new QuestionnaireWrapper(m_questionnaire);
            Assert.AreEqual(
                expected: m_questionnaire.QuestionnaireId,
                actual: wrapper.QuestionnaireId);
            Assert.AreEqual(
                expected: m_questionnaire.QuestionnaireDisplayName,
                actual: wrapper.QuestionnaireDisplayName);

            Assert.AreEqual(
                expected: m_questionnaire.Questions.Count,
                actual: wrapper.Questions.Count);
        }

        [Test]
        public void WhenQuestionWrapperGivenModel_ShouldbeContainedInModelProperty()
        {
            var wrapper = new QuestionWrapper(m_inputQuestion1);
            Assert.AreEqual(expected: m_inputQuestion1, actual: wrapper.Model);
        }

        [Test]
        public void WhenQuestionWrapperGivenNullModel_ShouldThrowArgumentNullException()
        {
            var constraint = Is.TypeOf<ArgumentNullException>()
                .And
                .Property(nameof(ArgumentNullException.ParamName))
                .EqualTo("model");

            Assert.Throws(constraint, () => new QuestionWrapper(null));
        }

        [Test]
        public void WhenQuestionWrapperHasModel_ShouldGetUnderlyingModelValues()
        {
            var wrapper = new QuestionWrapper(m_inputQuestion1);
            Assert.AreEqual(
                expected: m_inputQuestion1.QuestionId,
                actual: wrapper.QuestionId);
            Assert.AreEqual(
                expected: m_inputQuestion1.QuestionText,
                actual: wrapper.QuestionText);
            Assert.AreEqual(
                expected: m_inputQuestion1.QuestionType,
                actual: wrapper.QuestionType);
            Assert.AreEqual(
                expected: m_inputQuestion1.ReadOnly,
                actual: wrapper.ReadOnly);
            Assert.AreEqual(
                expected: m_inputQuestion1.Visible,
                actual: wrapper.Visible);
            m_inputQuestion1.Value = "10";
            Assert.AreEqual(
                expected: m_inputQuestion1.Value,
                actual: wrapper.Value);
        }
        
        [Test]
        public void WhenUpdatingQuestionWrapper_ShouldSetUnderlyingModelValues()
        {
            var wrapper = new QuestionWrapper(m_inputQuestion1) {Value = "100"};
            Assert.AreEqual(expected: "100", actual:m_inputQuestion1.Value);
        }
    }
}
