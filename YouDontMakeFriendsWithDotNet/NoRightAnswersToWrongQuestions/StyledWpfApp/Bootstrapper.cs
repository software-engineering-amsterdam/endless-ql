using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using QlsParser;
using QlsTransformer;
using QuestionnaireDomain.Entities;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;
using QuestionnaireOrchestration;
using QuestionnaireUI;
using StyledWpfApp.DataProvider;
using StyledWpfApp.ViewModels;

namespace StyledWpfApp
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
            collection.AddModule(new QlsTransformerModule());
            collection.AddModule(new QlsParserModule());
            collection.AddTransient(
                typeof(IStyledQuestionnaireViewModel),
                typeof(StyledQuestionnaireViewModel));
            collection.AddSingleton(
                typeof(IQuestionnaireDataProvider),
                typeof(QuestionnaireDataProvider));

            return collection;
        }
    }
}
