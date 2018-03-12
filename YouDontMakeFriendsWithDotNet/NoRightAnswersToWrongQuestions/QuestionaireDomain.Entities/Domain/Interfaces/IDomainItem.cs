using System;

namespace QuestionnaireDomain.Entities.Domain.Interfaces
{
    public interface IDomainItem
    {
        Guid Id { get; }
        string DisplayName { get; }
    }
}