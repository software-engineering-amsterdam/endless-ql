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

        [SetUp]
        public void Init()
        {
            m_questionnaire = new QuestionnaireModel(
                new Guid("E746E1F1-0A3A-400E-9824-C28427D51CD6"),
                "TestQuestionaire");

            m_inputQuestion1 = new QuestionModel(
                new Guid("40E98F85-949C-48F0-8194-CECEEBD0177F"),
                "input question",
                true,
                false,
                typeof(int));

        }


        [Test]
        public void WhenQuestionnaireWrapperGivenModel_ShouldbeContainedInModelProperty()
        {
            var wrapper = new QuestionnaireWrapper(m_questionnaire);
            Assert.AreEqual(expected: m_questionnaire, actual: wrapper.Model);
        }


        [Test]
        public void WhenQuestionWrapperGivenModel_ShouldbeContainedInModelProperty()
        {
            var wrapper = new QuestionWrapper(m_inputQuestion1);
            Assert.AreEqual(expected: m_inputQuestion1, actual: wrapper.Model);
        }

        [Test]
        public void WhenGivenNullModel_ShouldThrowArgumentNullException()
        {
            var constraint = Is.TypeOf<ArgumentNullException>()
                .And
                .Property(nameof(ArgumentNullException.ParamName))
                .EqualTo("model");

            Assert.Throws(constraint, () => new QuestionnaireWrapper(null));
        }

        [Test]
        public void WhenUpdatingWrapper_ShouldUpdateUnderlyingModel()
        {
            var constraint = Is.TypeOf<ArgumentNullException>()
                .And
                .Property(nameof(ArgumentNullException.ParamName))
                .EqualTo("model");

            Assert.Throws(constraint, () => new QuestionnaireWrapper(null));
        }
    }
}
