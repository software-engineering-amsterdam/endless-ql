using System;
using System.ComponentModel.Design;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QuestionaireDomain.Entities.API;
using QuestionnaireDomain.Logic;
using QuestionnaireDomain.Logic.API;
using QuestionnaireDomain.Logic.Logic;
using QuestionnaireInfrastructure.API;

namespace UnitTests.Domain.UnitTests
{
    [TestFixture]
    public class CreateQuestionnaireTests
    {
        private IServiceProvider m_serviceProvider;

        [SetUp]
        public void Init()
        {
            var services = new ServiceCollection();
            services.AddModule(new AntlrModule());
            services.AddModule(new DomainLogicModule());
            m_serviceProvider = services.BuildServiceProvider();
        }

        [Test]
        public void WhenGivenMalformedDefinition_ThrowsArgumentException()
        {
            try
            {
                var questionnaire = m_serviceProvider.GetService<IQuestionnaireCreator>();
                questionnaire.Create("gobbldygook");
            }
            catch (QlParserException exception)
            {
                Assert.AreEqual(
                    expected: @"Parse failed. See inner exception for details.",
                    actual: exception.Message);
                return;
            }

            Assert.Fail("Should have thrown an QlParserException exception");
        }

        [Test]
        public void WhenGivenWellFormedDefinition_ReturnsDomainObjects()
        {
            var questionnaire = m_serviceProvider.GetService<IQuestionnaireCreator>();
            var validText = @"form MyForm {}";
            var result = questionnaire.Create(validText);
            Assert.IsNotNull(result);
            Assert.AreEqual(expected: "MyForm", actual: result.FormName);
        }
    }
}
