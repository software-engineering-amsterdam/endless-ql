using System;
using System.Collections.Generic;
using QuestionaireOrchestration.API;
using QuestionaireOrchestration.CommandHandlers;

namespace QuestionaireOrchestration.QueryServices
{
    internal class CommandQueryService : ICommandQueryService
    {
        private readonly ICommandObjectRegistry m_registry;

        public CommandQueryService(ICommandObjectRegistry registry)
        {
            m_registry = registry;
        }

        public IEnumerable<Guid> GetDomainObjectIds(Guid commandId)
        {
            return m_registry.Find(commandId);
        }
    }
}