using System;
using System.Linq;
using Microsoft.Extensions.DependencyInjection;
using Moq;
using NUnit.Framework;
using QuestionaireDomain.Entities.DomainObjects;
using QuestionaireOrchestration;
using QuestionaireOrchestration.API;
using QuestionnaireDomain.Logic.API;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;

namespace UnitTests.Orchestration.UnitTests
{
    [TestFixture]
    public class CreateQuestionnaireCommandTests
    {
        private ServiceCollection m_services;

        [SetUp]
        public void Init()
        {
            m_services = new ServiceCollection();
            m_services.AddModule(new InfrastructureModule());
            m_services.AddModule(new OrchestrationModule());
        }

        //[Test]
        //public void CreateQuestionnaireCommand_CausesDomainItemToBeCreated()
        //{
        //    var commandMessage = new CreateQuestionnaireCommandMessage();
        //    var commandId = commandMessage.Id;

        //    var domainItemId = new Reference<IQuestionnaireRootNode>(Guid.NewGuid());
        //    var mockCreator = new Mock<IQuestionnaireAstCreator>();
        //    mockCreator.Setup(x => x.Create(It.IsAny<string>())).Returns(domainItemId);
        //    m_services.AddTransient(typeof(IQuestionnaireAstCreator), x => mockCreator.Object);
        //    var serviceProvider = m_services.BuildServiceProvider();
        //    var commandBus = serviceProvider.GetService<ICommandBus>();
        //    var commandQueryService = serviceProvider.GetService<ICommandQueryService>();

        //    commandBus.Send(commandMessage);
        //    var createdDomainItemId = commandQueryService
        //        .GetDomainObjectIds(commandId)
        //        ?.FirstOrDefault();

        //    Assert.AreEqual(expected: domainItemId, actual: createdDomainItemId);
        //}
    }
}
