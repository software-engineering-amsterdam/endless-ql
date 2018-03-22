using Microsoft.Extensions.DependencyInjection;
using QuestionnaireInfrastructure.API;
using QuestionnaireInfrastructure.Logic;

namespace QuestionnaireInfrastructure
{
    public class InfrastructureModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddSingleton<ICommandBus, CommandBus>();
            appRegistration.AddTransient(
                typeof(IQuestionnaireDefinitionLoader),
                typeof(QuestionnaireDefinitionLoader));
            appRegistration.AddSingleton(typeof(IIdMaker), typeof(IdMaker));
        }
    }
}