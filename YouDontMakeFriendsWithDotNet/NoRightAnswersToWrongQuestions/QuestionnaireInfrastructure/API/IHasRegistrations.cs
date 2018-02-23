using Microsoft.Extensions.DependencyInjection;

namespace QuestionnaireInfrastructure.API
{
    public interface IHasRegistrations
    {
        void RegisterDependencies(IServiceCollection appRegistration);
    }
}