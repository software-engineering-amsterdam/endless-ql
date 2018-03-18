using System;
using QuestionaireOrchestration.Models;

namespace QuestionaireOrchestration.QueryServices.Interfaces
{
    public interface IQuestionnaireOutputModelQueryService : 
        IModelQueryService<QuestionnaireOutputModel>
    {
        QuestionnaireModel GetModel(Guid id);
    }
}