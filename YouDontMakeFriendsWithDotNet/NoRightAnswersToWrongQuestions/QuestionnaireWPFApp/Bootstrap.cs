using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using Prism.Events;
using QuestionaireOrchestration;
using QuestionaireOrchestration.API;
using QuestionnaireDomain.Logic;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;
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
            collection.AddModule(new DomainLogicModule());
            collection.AddModule(new OrchestrationModule());
            collection.AddModule(new UiModule());

            collection.AddSingleton(typeof(IEventAggregator), typeof(EventAggregator));
            collection.AddTransient(typeof(INavigationViewModel), typeof(NavigationViewModel));
            collection.AddTransient(typeof(IQuestionnaireViewModel), typeof(QuestionnaireViewModel));
            collection.AddTransient(typeof(IMainViewModel), typeof(MainViewModel));

            return collection;
        }
    }
}
