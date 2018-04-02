using System;
using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;
using QuestionnaireOrchestration.Models;
using QuestionnaireOrchestration.QueryServices.Interfaces;

namespace QuestionnaireOrchestration.QueryServices
{
    internal sealed class QuestionOutputModelQueryService :
        ModelQueryServiceBase<QuestionOutputModel>,
        IQuestionOutputModelQueryService
    {
        private readonly ISymbolTable m_symbolTable;

        public QuestionOutputModelQueryService(
            IDomainItemLocator domainItemLocator,
            ISymbolTable symbolTable)
            : base(domainItemLocator)
        {
            m_symbolTable = symbolTable;
        }

        public override IEnumerable<QuestionOutputModel> GetAll()
        {
            return DomainItemLocator
                .GetAll<IQuestionOutputItem>()
                .Select(x => new QuestionOutputModel(x.Id, x.DisplayName));
        }

        public QuestionModel GetQuestionModel(Guid questionId)
        {
            var question = DomainItemLocator
                .Get<IQuestionOutputItem>(questionId);

            var variable = DomainItemLocator
                .Get<IQuestionNode>(question.Variable.Id);

            return new QuestionModel(
                question.Id,
                question.Variable.Id,
                question.QuestionText,
                question.Visible,
                question.ReadOnly,
                question.QuestionType)
            {
                Value = question
                    .QuestionType
                    .GetValue(m_symbolTable, variable)
            };
        }
    }
}