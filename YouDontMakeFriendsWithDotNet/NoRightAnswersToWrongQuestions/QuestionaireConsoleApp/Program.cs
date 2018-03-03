using System;
using AntlrInterpretor;
using Microsoft.Extensions.DependencyInjection;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireOrchestration;
using QuestionaireOrchestration.Visitors;
using QuestionnaireDomain.Logic;
using QuestionnaireDomain.Logic.API;
using QuestionnaireInfrastructure;
using QuestionnaireInfrastructure.API;

namespace QuestionaireConsoleApp
{
    class Program
    {
        public static IServiceCollection QlServiceCollection { get; set; }
        public static IServiceProvider m_serviceProvider;


        static void Main(string[] args)
        {
            InitializeModules();
            Console.WriteLine("Enter form:");
            var formDefinition = Console.ReadLine();
            var creator = m_serviceProvider.GetService<IQuestionnaireCreator>();
            var questionaireId = creator.Create(formDefinition);
            var domainItemLocator = m_serviceProvider.GetService<IDomainItemLocator>();
            var ast = domainItemLocator.Get<IRootNode>(questionaireId);
            var writer = m_serviceProvider.GetService<IQuestionnairePrinter>();
            writer.Writer = Console.Out;
            writer.Print(ast);
            Console.ReadLine();
        }

        private static void InitializeModules()
        {
            var ioc = new DependencyInjectionContainer();
            QlServiceCollection = ioc.Create();

            QlServiceCollection.AddModule(new InfrastructureModule());
            QlServiceCollection.AddModule(new AntlrModule());
            QlServiceCollection.AddModule(new DomainLogicModule());
            QlServiceCollection.AddModule(new OrchestrationModule());
            m_serviceProvider = QlServiceCollection.BuildServiceProvider(true);

        }
    }
}
