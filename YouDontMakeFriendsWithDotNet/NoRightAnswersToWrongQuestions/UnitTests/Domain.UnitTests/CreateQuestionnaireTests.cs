using System;
using NUnit.Framework;
using QuestionnaireDomain.Logic.Logic;

namespace UnitTests.Domain.UnitTests
{
    [TestFixture]
    public class CreateQuestionnaireTests
    {
        [Test]
        public void WhenGivenMalformedDefinition_ThrowsArgumentException()
        {
            try
            {
                var questionnaire = new QuestionnaireCreator();
                questionnaire.Create("this is a malformed definition");
            }
            catch (ArgumentException)
            {
                return;
            }

            Assert.Fail("Should have thrown an argument exception");
        }

        [Test]
        public void WhenGivenWellFormedDefinition_ReturnsDomainObjects()
        {
            var questionnaire = new QuestionnaireCreator();
            var validText = @"form MyForm {}";
            var result = questionnaire.Create(validText);
            Assert.IsNotNull(result);
            Assert.AreEqual(expected: "MyForm", actual:result.FormName);
        }
    }
}
