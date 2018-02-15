using System.ComponentModel.Design;
using Microsoft.Extensions.DependencyInjection;
using QuestionaireOrchestration.API;
using QuestionaireOrchestration.CommandHandlers;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration
{
    public class OrchestrationModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddTransient(typeof(ICommandHandler<ParseTextCommandMessage>), typeof(ParseTextCommandHandler));
        }
    }
}