using System;
using System.Collections.Generic;

namespace QuestionnaireOrchestration.QueryServices.Interfaces
{
    public interface ICommandQueryService
    {
        IEnumerable<Guid> GetDomainObjectIds(Guid commandId);
    }
}