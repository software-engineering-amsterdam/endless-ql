using System.Collections.Generic;
using System.Linq;
using QuestionaireOrchestration.Models;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace QuestionaireOrchestration.QueryServices
{
    internal sealed class QuestionnaireDefintionQueryService : ModelQueryServiceBase<QuestionnaireDefinitionModel>
    {
        public QuestionnaireDefintionQueryService(
            IDomainItemLocator domainItemLocator) 
            : base(domainItemLocator)
        {
        }

        public override IEnumerable<QuestionnaireDefinitionModel> GetAll()
        {
            return m_domainItemLocator
                .GetAll<IQuestionnaireRootNode>()
                .Select(x => new QuestionnaireDefinitionModel(x.Id, x.DisplayName));
        }
    }
}