using System;
using System.Collections.Generic;
using System.Linq;
using QlsTransformer.Orchestration.Models;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;
using QuestionnaireOrchestration.Models;
using QuestionnaireOrchestration.QueryServices;
using QuestionnaireOrchestration.QueryServices.Interfaces;

namespace QlsTransformer.Orchestration.QueryServices
{
    internal class StyledQuestionnaireModelQueryService :
        ModelQueryServiceBase<StyledQuestionnaireModel>, 
        IStyledQuestionnaireModelQueryService
    {
        public StyledQuestionnaireModelQueryService(IDomainItemLocator domainItemLocator) : 
            base(domainItemLocator)
        {
        }

        public override IEnumerable<StyledQuestionnaireModel> GetAll()
        {
            // ToDo: this is a hack - do I even need this?
            return DomainItemLocator
                .GetAll<IQuestionOutputItem>()
                .Select(x => new StyledQuestionnaireModel(x.Id, x.DisplayName));
        }

        public StyledQuestionnaireModel GetModel(Guid firstItemId)
        {
            return GetAll().FirstOrDefault(x => x.QuestionnaireId == firstItemId);
        }
    }
    

    public interface IStyledQuestionnaireModelQueryService
    {
        IEnumerable<StyledQuestionnaireModel> GetAll();

        StyledQuestionnaireModel GetModel(Guid firstItemId);
    }
}
