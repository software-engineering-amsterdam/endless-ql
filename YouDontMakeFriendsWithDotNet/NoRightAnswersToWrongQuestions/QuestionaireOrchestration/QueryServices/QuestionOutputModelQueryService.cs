using System;
using System.Collections.Generic;
using System.Linq;
using QuestionaireOrchestration.Models;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionaireOrchestration.QueryServices
{
    internal sealed class QuestionOutputModelQueryService :
        ModelQueryServiceBase<QuestionOutputModel>,
        IQuestionOutputModelQueryService
    {
        public QuestionOutputModelQueryService(
            IDomainItemLocator domainItemLocator)
            : base(domainItemLocator)
        {
        }

        public override IEnumerable<QuestionOutputModel> GetAll()
        {
            return m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .Select(x => new QuestionOutputModel(x.Id, x.DisplayName));
        }

        public QuestionModel GetQuestionModel(Guid questionId)
        {
            var question = m_domainItemLocator
                .Get<IQuestionOutputItem>(questionId);

            return new QuestionModel(
                question.Id,
                question.VariableId,
                question.QuestionText,
                question.Visible,
                question.ReadOnly,
                question.QuestionType)
            {
                //ToDo: add value
            };
        }
    }
}