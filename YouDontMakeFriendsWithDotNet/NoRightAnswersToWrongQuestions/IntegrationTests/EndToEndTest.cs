using System;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.DependencyInjection.Extensions;
using NUnit.Framework;
using QuestionaireOrchestration;
using QuestionaireOrchestration.API;
using QuestionnaireDomain.Logic;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;
using QuestionnaireUI;

namespace IntegrationTests
{
    [TestFixture]
    public class EndToEndTest
    {
        [Test]
        public void Skeleton()
        {
            var app = new TestApp();
            app.TakeInput("form MyForm {}");
            Assert.IsTrue(app.UiWasCreated);
        }
    }

    public class TestApp : QLApp
    {
        private readonly IServiceProvider m_serviceProvider;
        public bool UiWasCreated { get; }
        public IServiceCollection QlServiceCollection { get; }

        public TestApp()
        {
            UiWasCreated = false;
            var ioc = new DependencyInjectionContainer();
            QlServiceCollection = ioc.Create();

            QlServiceCollection.AddModule(new InfrastructureModule());
            QlServiceCollection.AddModule(new AntlrModule());
            QlServiceCollection.AddModule(new DomainLogicModule());
            QlServiceCollection.AddModule(new OrchestrationModule());
            QlServiceCollection.AddModule(new UiModule());
            QlServiceCollection.AddSingleton(typeof(IServiceProvider), x => m_serviceProvider);
            m_serviceProvider = QlServiceCollection.BuildServiceProvider(true);
        }

        public void TakeInput(string textToParse)
        {
            var commandBus = m_serviceProvider.GetService<ICommandBus>();

            var commandMessage = new ParseTextCommandMessage {Text = textToParse};
            commandBus.Send(commandMessage);
        }
    }
}
