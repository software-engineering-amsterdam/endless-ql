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

namespace SimpleWPFApp
{
    public class Bootstrapper
    {
        public IServiceCollection Bootstrap()
        {
            var ioc = new DependencyInjectionContainer();
            var collection = ioc.Create();

            collection.AddModule(new InfrastructureModule());
            collection.AddModule(new OrchestrationModule());
            collection.AddModule(new UiModule());
            collection.AddTransient(typeof(IQuestionnaireViewModel), typeof(QuestionnaireViewModel));
            return collection;
        }
    }
}
