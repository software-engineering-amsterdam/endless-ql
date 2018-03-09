using System.Collections.Generic;
using System.Runtime.CompilerServices;
using QuestionaireDomain.Entities.API.Output;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireOrchestration.API
{
    public interface IQuestionnaireQueryService
    {
        IEnumerable<ModelReference<IQuestionnaireOutputItem>> GetAll();
    }
}