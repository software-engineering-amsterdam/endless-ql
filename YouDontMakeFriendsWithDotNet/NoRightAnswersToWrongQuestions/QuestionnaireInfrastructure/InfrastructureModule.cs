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
        }
    }
}