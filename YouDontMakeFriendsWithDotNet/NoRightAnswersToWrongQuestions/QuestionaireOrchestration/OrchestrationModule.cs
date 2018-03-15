using Microsoft.Extensions.DependencyInjection;
using QuestionaireOrchestration.CommandHandlers;
using QuestionaireOrchestration.Commands;
using QuestionaireOrchestration.Models;
using QuestionaireOrchestration.QueryServices;
using QuestionaireOrchestration.QueryServices.Interfaces;
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
                typeof(ICommandHandler<LoadDefinitionsFromFileCommand>),
                typeof(LoadDefinitionsFromFileCommandHandler));

            appRegistration.AddTransient(
                typeof(ICommandHandler<CreateDefinitionFromTextCommand>),
                typeof(CreateDefinitionFromTextCommandHandler));

            appRegistration.AddTransient(
                typeof(ICommandHandler<CreateQuestionnaireCommandMessage>), 
                typeof(ParseTextCommandHandler));

            appRegistration.AddTransient(
                typeof(IModelQueryService<QuestionnaireDefinitionModel>),
                typeof(QuestionnaireDefintionQueryService));
        }
    }
}   