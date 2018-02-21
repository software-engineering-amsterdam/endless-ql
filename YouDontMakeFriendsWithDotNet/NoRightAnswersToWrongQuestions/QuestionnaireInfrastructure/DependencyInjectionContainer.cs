using Microsoft.Extensions.DependencyInjection;

namespace QuestionnaireInfrastructure
{
    public class DependencyInjectionContainer
    {
        public IServiceCollection Create()
        {
            var services = new ServiceCollection();
            
            return services;
        }
    }
}