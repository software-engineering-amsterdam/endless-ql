using System;
using System.Configuration;
using System.Linq;
using System.Runtime.Remoting.Channels;
using Microsoft.Extensions.DependencyInjection;

namespace QuestionnaireInfrastructure.API
{
    public static class DependencyInjectionExtensions
    {
        public static IServiceCollection AddModule(
            this IServiceCollection serviceCollection,
            IHasRegistrations registrations)
        {
            registrations.RegisterDependencies(serviceCollection);
            return serviceCollection;
        }
    }
}