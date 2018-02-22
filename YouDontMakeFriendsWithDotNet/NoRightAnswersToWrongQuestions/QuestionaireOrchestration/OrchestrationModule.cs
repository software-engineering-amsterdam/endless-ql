using Microsoft.Extensions.DependencyInjection;
using QuestionaireOrchestration.API;
using QuestionaireOrchestration.CommandHandlers;
using QuestionaireOrchestration.QueryServices;
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
        }
    }
}