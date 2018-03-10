using Microsoft.Extensions.DependencyInjection;
using QuestionaireDomain.Entities.API;
using QuestionnaireInfrastructure.API;

namespace QuestionaireDomain.Entities
{
    public class EntitiesModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddSingleton(typeof(IAstFactory), typeof(AstFactory));
            appRegistration.AddSingleton(typeof(IOutputItemFactory), typeof(OutputItemFactory));
            appRegistration.AddSingleton(typeof(IDomainItemRegistry), typeof(DomainItemRegistry));
        }
    }
}
