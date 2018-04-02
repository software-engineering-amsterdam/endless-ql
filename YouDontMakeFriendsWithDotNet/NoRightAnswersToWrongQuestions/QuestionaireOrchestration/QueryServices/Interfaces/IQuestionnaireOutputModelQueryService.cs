using System;
using QuestionnaireOrchestration.Models;

namespace QuestionnaireOrchestration.QueryServices.Interfaces
{
    public interface IQuestionnaireOutputModelQueryService :
        IModelQueryService<QuestionnaireOutputModel>
    {
        QuestionnaireModel GetModel(Guid id);
    }
}