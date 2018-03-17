using QuestionnaireInfrastructure;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using QuestionaireOrchestration;
using QuestionnaireDomain.Entities;
using QuestionnaireInfrastructure.API;
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
