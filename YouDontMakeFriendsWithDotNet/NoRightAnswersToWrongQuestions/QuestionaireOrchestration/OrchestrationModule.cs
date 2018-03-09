using Microsoft.Extensions.DependencyInjection;
using QuestionaireOrchestration.API;
using QuestionaireOrchestration.CommandHandlers;
using QuestionaireOrchestration.Visitors;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration
{
    public class OrchestrationModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddTransient(
                typeof(ICommandHandler<CreateQuestionnaireCommandMessage>), 
                typeof(ParseTextCommandHandler));
            appRegistration.AddTransient(
                typeof(ICommandHandler<LoadQuestionnaireDefinitionsCommand>),
                typeof(LoadQuestionnaireDefinitionsCommandHandler));
            appRegistration.AddTransient(
                typeof(IQuestionnairePrinter), 
                typeof(QuestionnairePrinter));
            appRegistration.AddTransient(
                typeof(IBooleanLogicPrinter), 
                typeof(BooleanLogicPrinter));
            appRegistration.AddTransient(
                typeof(ICommandHandler<CreateQuestionnaireCommandMessage>), 
                typeof(ParseTextCommandHandler));

            appRegistration.AddTransient(
                typeof(IQuestionnaireQueryService),
                typeof(QuestionnaireQueryService));
        }
    }
}   