using QuestionnaireInfrastructure;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using QuestionnaireDomain.Entities;
using QuestionnaireInfrastructure.API;
using QuestionnaireOrchestration;
using QuestionnaireUI;
using SimpleWPFApp.DataProvider;

namespace SimpleWPFApp
{
    public class Bootstrapper
    {
        public IServiceCollection Bootstrap()
        {
            var ioc = new DependencyInjectionContainer();
            var collection = ioc.Create();

            collection.AddModule(new InfrastructureModule());
            collection.AddModule(new AntlrModule());
            collection.AddModule(new EntitiesModule());
            collection.AddModule(new OrchestrationModule());
            collection.AddModule(new UiModule());
            collection.AddTransient(
                typeof(IQuestionnaireViewModel),
                typeof(QuestionnaireViewModel));
            collection.AddSingleton(
                typeof(IQuestionnaireDataProvider),
                typeof(QuestionnaireDataProvider));

            return collection;
        }
    }
}
