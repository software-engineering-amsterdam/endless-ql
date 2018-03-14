using System.Collections.Generic;
using QuestionaireOrchestration.Models;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionaireOrchestration.QueryServices.Interfaces
{
    public interface IQuestionnaireQueryService
    {
        IEnumerable<ModelReference<IQuestionnaireOutputItem>> GetAll();
    }
}