using System.Collections.Generic;
using System.Runtime.CompilerServices;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionaireOrchestration.API
{
    public interface IQuestionnaireQueryService
    {
        IEnumerable<ModelReference<IQuestionnaireOutputItem>> GetAll();
    }
}