using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using Prism.Events;
using QuestionnaireDomain.Entities;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;
using QuestionnaireOrchestration;
using QuestionnaireUI;
using QuestionnaireWPFApp.ViewModels;

namespace QuestionnaireWPFApp
{
    public class Bootstrapper
    {
        public IServiceCollection Bootstrap()
        {
            var ioc = new DependencyInjectionContainer();
            var collection  = ioc.Create();

            collection.AddModule(new InfrastructureModule());
            collection.AddModule(new AntlrModule());
            collection.AddModule(new EntitiesModule());
            collection.AddModule(new OrchestrationModule());
            collection.AddModule(new UiModule());

            collection.AddSingleton(typeof(IEventAggregator), typeof(EventAggregator));
            collection.AddTransient(typeof(INavigationViewModel), typeof(NavigationViewModel));
            collection.AddTransient(typeof(IQuestionnaireViewModel), typeof(QuestionnaireViewModel));
            collection.AddSingleton(typeof(IMainViewModel), typeof(MainViewModel));
            collection.AddSingleton(typeof(IMessageDialogService), typeof(MessageDialogService));

            return collection;
        }
    }
}
