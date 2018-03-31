using System;
using System.Collections.Generic;
using QlsTransformer.Orchestration.Models;

namespace QlsTransformer.Orchestration.QueryServices
{
    public interface IStyledQuestionnaireModelQueryService
    {
        IEnumerable<StyledQuestionnaireModel> GetAll();

        StyledQuestionnaireModel GetModel(Guid firstItemId);
    }
}