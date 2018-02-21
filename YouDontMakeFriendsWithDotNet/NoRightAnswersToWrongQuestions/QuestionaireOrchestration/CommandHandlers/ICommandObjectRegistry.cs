using System;
using System.Collections.Generic;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.CommandHandlers
{
    internal interface ICommandObjectRegistry
    {
        void Add(ICommandMessage command, Guid domainItemId);
        void Add(ICommandMessage command, IEnumerable<Guid> domainItemIds);
        IEnumerable<Guid> Find(Guid id);
    }

    internal class CommandObjectRegistry : ICommandObjectRegistry
    {
        private static readonly Dictionary<Guid, IEnumerable<Guid>> Registry;

        static CommandObjectRegistry()
        {
            Registry = new Dictionary<Guid, IEnumerable<Guid>>();
        }

        public void Add(ICommandMessage command, Guid domainItemId)
        {
            Add(command, new[] {domainItemId});
        }

        public void Add(ICommandMessage command, IEnumerable<Guid> domainItemIds)
        {
            Registry.Add(command.Id, domainItemIds);
        }

        public IEnumerable<Guid> Find(Guid id)
        {
            return Registry[id];
        }
    }
}