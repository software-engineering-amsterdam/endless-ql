using System;
using QuestionaireOrchestration.Models;

namespace QuestionaireOrchestration.QueryServices.Interfaces
{
    public interface IQuestionOutputModelQueryService : 
        IModelQueryService<QuestionOutputModel>
    {
        QuestionModel GetQuestionModel(Guid questionId);
    }
}