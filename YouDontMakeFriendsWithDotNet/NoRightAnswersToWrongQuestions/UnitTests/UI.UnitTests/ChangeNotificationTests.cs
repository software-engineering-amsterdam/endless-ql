using System;
using NUnit.Framework;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireOrchestration.Models;
using QuestionnaireUI.Models;

namespace QL.UnitTests.UI.UnitTests
{
    [TestFixture]
    public class ChangeNotificationTests
    {
        private QuestionModel m_inputQuestion1;

        [SetUp]
        public void Init()
        {
            m_inputQuestion1 = new QuestionModel(
                new Guid("40E98F85-949C-48F0-8194-CECEEBD0177F"),
                new Guid("E1994F74-EBF3-4EAE-9E57-A32238B613E1"),
                "int input question",
                true,
                false,
                new IntegerQuestionType());
        }

        [Test]
        public void WhenPropertyChanged_ShouldRaisePropertyChangedEvent()
        {
            var propertyEventWasRaised = false;
            var wrapper = new QuestionWrapper(m_inputQuestion1);
            wrapper.PropertyChanged += (s, e) =>
            {
                if (e.PropertyName == "Value")
                {
                    propertyEventWasRaised = true;
                }
            };

            wrapper.Value = "999";

            Assert.IsTrue(propertyEventWasRaised);
        }
    }
}
