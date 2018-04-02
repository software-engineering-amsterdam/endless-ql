using System;
using QuestionnaireOrchestration.Models;

namespace QuestionnaireOrchestration.QueryServices.Interfaces
{
    public interface IQuestionOutputModelQueryService :
        IModelQueryService<QuestionOutputModel>
    {
        QuestionModel GetQuestionModel(Guid questionId);
    }
}