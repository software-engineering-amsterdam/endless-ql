using System;
using System.Linq;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using NUnit.Framework;
using QuestionaireDomain.Entities;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.Output;
using QuestionnaireDomain.Logic;
using QuestionnaireDomain.Logic.API;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;

namespace UnitTests.Domain.UnitTests
{
    [TestFixture]
    public class CreateModelTests
    {
        private IServiceProvider m_serviceProvider;
        private IDomainItemLocator m_domainItemLocator;
        private IQuestionnaireModelCreator m_modelCreator;
        [SetUp]
        public void Init()
        {
            var services = new ServiceCollection();
            services.AddModule(new AntlrModule());
            services.AddModule(new InfrastructureModule());
            services.AddModule(new EntitiesModule());
            services.AddModule(new DomainLogicModule());
            m_serviceProvider = services.BuildServiceProvider();
            m_domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
            m_modelCreator = m_serviceProvider.GetService<IQuestionnaireModelCreator>();
        }

        [TearDown]
        public void Cleanup()
        {
            //ToDo: this is a hack, should fix lifetime of service (possibly)
            var registry = m_serviceProvider.GetService<IDomainItemRegistry>();
            registry.Nuke();
        }


        [TestCaseSource(
            typeof(TestModelCreationData),
            nameof(TestModelCreationData.SimpleQuestionnaireCases))]
        public void WhenGivenBadlyFormedComments_ThrowsException(
            string validDescription,
            string expectedQuestionnaireName)
        {
            CreateForm(validDescription);
            var actualDisplayName = m_domainItemLocator
                .GetAll<IQuestionnaireOutputItem>()
                .FirstOrDefault()
                ?.DisplayName;

            Assert.AreEqual(
                expected: expectedQuestionnaireName,
                actual: actualDisplayName);
        }
        
        private void CreateForm(string validText)
        {
            var questionnaireCreator = m_serviceProvider
                .GetService<IQuestionnaireAstCreator>();
           
            questionnaireCreator.Create(validText);

            var questionnaireNodes = m_domainItemLocator
                .GetAll<IQuestionnaireRootNode>();

            foreach (var questionnaireRootNode in questionnaireNodes)
            {
                if (m_modelCreator.Validate(questionnaireRootNode))
                {
                    m_modelCreator.Create(questionnaireRootNode);
                }
            }
        }

    }
}
