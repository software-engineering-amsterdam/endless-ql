using System;
using Microsoft.Extensions.DependencyInjection;
using QuestionnaireInfrastructure.API;

namespace QuestionnaireInfrastructure.Logic
{
    internal class CommandBus : ICommandBus
    {
        private readonly IServiceProvider m_serviceProvider;

        public CommandBus(IServiceProvider serviceProvider)
        {
            m_serviceProvider = serviceProvider;
        }
        
        public void Send<T>(T command) where T : ICommandMessage 
        {
            var commandHandler = m_serviceProvider.GetService<ICommandHandler<T>>();
            commandHandler.Execute(command);
        }
    }
}