using System;
using System.Collections.Generic;

namespace QuestionaireOrchestration.QueryServices.Interfaces
{
    public interface ICommandQueryService
    {
        IEnumerable<Guid> GetDomainObjectIds(Guid commandId);
    }
}