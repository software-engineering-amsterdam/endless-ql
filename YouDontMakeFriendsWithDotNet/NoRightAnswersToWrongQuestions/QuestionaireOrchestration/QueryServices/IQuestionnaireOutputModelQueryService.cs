using System;
using System.Collections.Generic;
using QuestionaireOrchestration.Models;
using QuestionaireOrchestration.QueryServices.Interfaces;

namespace QuestionaireOrchestration.QueryServices
{
    public interface IQuestionnaireOutputModelQueryService : 
        IModelQueryService<QuestionnaireOutputModel>
    {
        QuestionnaireModel GetModel(Guid id);
    }
}