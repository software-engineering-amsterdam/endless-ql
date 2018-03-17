using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using QuestionaireOrchestration.Models;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionaireOrchestration.QueryServices
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
            return m_domainItemLocator
                .GetAll<IQuestionOutputItem>()
                .Select(x => new QuestionOutputModel(x.Id, x.DisplayName));
        }

        public QuestionModel GetQuestionModel(Guid questionId)
        {
            var question = m_domainItemLocator
                .Get<IQuestionOutputItem>(questionId);
            var variable = m_domainItemLocator
                .Get<IQuestionNode>(question.Variable.Id);

            return new QuestionModel(
                question.Id,
                question.Variable.Id,
                question.QuestionText,
                question.Visible,
                question.ReadOnly,
                question.QuestionType)
            {
                Value = GetValue(variable)
            };
        }


        private string GetValue(IQuestionNode question)
        {
            var type = question.QuestionType;
            if (type == typeof(bool))
            {
                return m_symbolTable.Lookup<bool>(question.Id).ToString();
            }

            if (type == typeof(string))
            {
                return m_symbolTable.Lookup<string>(question.Id) ?? "";
            }

            if (type == typeof(decimal))
            {
                return m_symbolTable.Lookup<decimal>(question.Id).ToString(CultureInfo.InvariantCulture);
            }

            if (type == typeof(int))
            {
                return m_symbolTable.Lookup<int>(question.Id).ToString(CultureInfo.InvariantCulture);
            }

            if (type == typeof(DateTime))
            {
                return m_symbolTable.Lookup<DateTime>(question.Id).ToString(CultureInfo.InvariantCulture);
            }

            throw new ArgumentException($@"value lookup for type '{type}' not implemented");
        }
    }
}