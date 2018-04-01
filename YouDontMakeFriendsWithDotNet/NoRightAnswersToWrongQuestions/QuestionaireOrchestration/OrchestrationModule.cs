using Microsoft.Extensions.DependencyInjection;
using QuestionnaireInfrastructure.API;
using QuestionnaireOrchestration.CommandHandlers;
using QuestionnaireOrchestration.Commands;
using QuestionnaireOrchestration.Models;
using QuestionnaireOrchestration.QueryServices;
using QuestionnaireOrchestration.QueryServices.Interfaces;

namespace QuestionnaireOrchestration
{
    public class OrchestrationModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddTransient(
                typeof(ICommandHandler<CreateQuestionnaireCommandMessage>), 
                typeof(ParseTextCommandHandler));

            appRegistration.AddTransient(
                typeof(ICommandHandler<CreateDefinitionFromTextCommand>),
                typeof(CreateDefinitionFromTextCommandHandler));

            appRegistration.AddTransient(
                typeof(ICommandHandler<CreateQuestionnaireCommandMessage>),
                typeof(ParseTextCommandHandler));

            appRegistration.AddTransient(
                typeof(ICommandHandler<UpdateValuesCommand>),
                typeof(UpdateValuesCommandHandler));

            appRegistration.AddTransient(
                typeof(IModelQueryService<QuestionnaireDefinitionModel>),
                typeof(QuestionnaireDefintionQueryService));

            appRegistration.AddTransient(
                typeof(IQuestionnaireOutputModelQueryService),
                typeof(QuestionnaireOutputModelQueryService));

            appRegistration.AddTransient(
                typeof(IQuestionOutputModelQueryService),
                typeof(QuestionOutputModelQueryService));

        }
    }
}   