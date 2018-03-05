using Microsoft.Extensions.DependencyInjection;
using QuestionaireOrchestration.API;
using QuestionaireOrchestration.CommandHandlers;
using QuestionaireOrchestration.QueryServices;
using QuestionaireOrchestration.Visitors;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration
{
    public class OrchestrationModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddTransient(typeof(ICommandHandler<CreateQuestionnaireCommandMessage>), typeof(ParseTextCommandHandler));
            appRegistration.AddTransient(typeof(ICommandQueryService), typeof(CommandQueryService));
            appRegistration.AddTransient(typeof(ICommandObjectRegistry), typeof(CommandObjectRegistry));
            appRegistration.AddTransient(typeof(IQuestionnairePrinter), typeof(QuestionnairePrinter));
            appRegistration.AddTransient(typeof(IBooleanLogicPrinter), typeof(BooleanLogicPrinter));
        }
    }
}