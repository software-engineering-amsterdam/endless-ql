using System;
using NUnit.Framework;

namespace UnitTests.UI.UnitTests
{
    [TestFixture]
    public class BasicTests
    {
        private QuestionnaireModel m_questionnaire;
        private Guid m_questionnaireId;

        [SetUp]
        public void Init()
        {
            m_questionnaireId = new Guid("E746E1F1-0A3A-400E-9824-C28427D51CD6");
            m_questionnaire = new QuestionnaireModel
            {
                Name="TestQuestionaire",
                Id= m_questionnaireId
            };
            
            var calculatedQuestion = new CalculatedQuestionModel()
            {
                Id = new Guid("50536A6C-CD2E-4C5E-9669-3CAAA09AD6E1"),
                Text = "Test Question Text",
                Value = "1234"
            };

            m_questionnaire.Statements.Add(calculatedQuestion);
        }


        [Test]
        public void WhenGivenModel_ShouldbeContainedInModelProperty()
        {
            var wrapper = new QuestionaireWrapper(m_questionnaire);
            Assert.AreEqual(expected: m_questionnaire, actual: wrapper.Model);
        }

        [Test]
        public void WhenGivenNullModel_ShouldThrowArgumentNullException()
        {
            var constraint = Is.TypeOf<ArgumentNullException>()
                .And
                .Property(nameof(ArgumentNullException.ParamName))
                .EqualTo("questionnaire");

            Assert.Throws(constraint, () => new QuestionaireWrapper(null));
        }

        [Test]
        public void WhenQuestionnaireNameIsNull_ShouldThrowArgumentNullException()
        {
            var constraint = Is.TypeOf<ArgumentException>()
                .And
                .Message
                .EqualTo("questionnaire name cannot be null");

            m_questionnaire.Name = null;
            Assert.Throws(constraint, () => new QuestionaireWrapper(m_questionnaire));
        }


        [Test]
        public void WhenCalculatedQuestionTextIsNull_ShouldThrowArgumentNullException()
        {
            var constraint = Is.TypeOf<ArgumentNullException>()
                .And
                .Property(nameof(ArgumentNullException.ParamName))
                .EqualTo("text");

            m_questionnaire.Name = null;
            var id = Guid.NewGuid();
            Assert.Throws(constraint, () => new CalculatedQuestionWrapper(id,null,"123"));
        }


        [Test]
        public void WhenCalculatedQuestionValueIsNull_ShouldThrowArgumentNullException()
        {
            var constraint = Is.TypeOf<ArgumentNullException>()
                .And
                .Property(nameof(ArgumentNullException.ParamName))
                .EqualTo("value");

            m_questionnaire.Name = null;
            var id = Guid.NewGuid();
            Assert.Throws(constraint, () => new CalculatedQuestionWrapper(id, "rewtr", null));
        }
    }
}
