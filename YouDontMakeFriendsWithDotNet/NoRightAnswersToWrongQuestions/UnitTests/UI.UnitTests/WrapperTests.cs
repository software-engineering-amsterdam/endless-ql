using System;
using NUnit.Framework;
using QuestionnaireUI.Models;

namespace UnitTests.UI.UnitTests
{
    [TestFixture]
    public class WrapperTests
    {
        private QuestionnaireModel m_questionnaire;

        [SetUp]
        public void Init()
        {
            m_questionnaire = new QuestionnaireModel
            {
                QuestionnaireDisplayName = "TestQuestionaire",
                QuestionnaireId = new Guid("E746E1F1-0A3A-400E-9824-C28427D51CD6")
            };
        }


        [Test]
        public void WhenGivenModel_ShouldbeContainedInModelProperty()
        {
            var wrapper = new QuestionnaireWrapper(m_questionnaire);
            Assert.AreEqual(expected: m_questionnaire, actual: wrapper.Model);
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

        //[Test]
        //public void WhenQuestionnaireNameIsNull_ShouldThrowArgumentNullException()
        //{
        //    var constraint = Is.TypeOf<ArgumentException>()
        //        .And
        //        .Message
        //        .EqualTo("questionnaire name cannot be null");

        //    m_questionnaire.Name = null;
        //    Assert.Throws(constraint, () => new QuestionnaireWrapper(m_questionnaire));
        //}


        //[Test]
        //public void WhenCalculatedQuestionTextIsNull_ShouldThrowArgumentNullException()
        //{
        //    var constraint = Is.TypeOf<ArgumentNullException>()
        //        .And
        //        .Property(nameof(ArgumentNullException.ParamName))
        //        .EqualTo("Text");

        //    m_calculatedQuestion.Text = null;
        //    Assert.Throws(constraint, () => new CalculatedQuestionWrapper(m_calculatedQuestion));
        //}

        //[Test]
        //public void WhenCalculatedQuestionValueIsNull_ShouldThrowArgumentNullException()
        //{
        //    var constraint = Is.TypeOf<ArgumentNullException>()
        //        .And
        //        .Property(nameof(ArgumentNullException.ParamName))
        //        .EqualTo("Value");

        //    m_calculatedQuestion.Value = null;
        //    Assert.Throws(constraint, () => new CalculatedQuestionWrapper(m_calculatedQuestion));
        //}


        //[Test]
        //public void WhenAskingForUnderlyinModelsName_ShouldReturnCorrectValue()
        //{
        //    var questionaireName = "Fred";
        //    m_questionnaire.Name = questionaireName;
        //    var wrapper = new QuestionnaireWrapper(m_questionnaire);
        //    Assert.AreEqual(
        //        expected: questionaireName,
        //        actual: wrapper.Name);
        //}

        //[Test]
        //public void WhenAskingForUnderlyinModelsCalculatedValue_ShouldReturnCorrectValue()
        //{
        //    var calculatedValue = "987";
        //    m_calculatedQuestion.Value = calculatedValue;
        //    var wrapper = new CalculatedQuestionWrapper(m_calculatedQuestion);
        //    Assert.AreEqual(
        //        expected: calculatedValue,
        //        actual: wrapper.Value);
        //}
    }
}
