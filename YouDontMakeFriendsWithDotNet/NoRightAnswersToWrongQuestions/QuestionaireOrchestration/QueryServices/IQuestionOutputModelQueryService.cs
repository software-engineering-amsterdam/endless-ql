using System;
using QuestionaireOrchestration.Models;
using QuestionaireOrchestration.QueryServices.Interfaces;

namespace QuestionaireOrchestration.QueryServices
{
    public interface IQuestionOutputModelQueryService : 
        IModelQueryService<QuestionOutputModel>
    {
        QuestionModel GetQuestionModel(Guid questionId);
    }
}