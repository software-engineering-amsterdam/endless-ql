using System;
using System.Collections.Generic;

namespace QuestionaireOrchestration.API
{
    public interface ICommandQueryService
    {
        IEnumerable<Guid> GetDomainObjectIds(Guid commandId);
    }
}