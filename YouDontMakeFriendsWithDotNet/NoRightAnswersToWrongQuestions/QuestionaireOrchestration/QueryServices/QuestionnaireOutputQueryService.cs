using System;
using System.Collections.Generic;
using System.Linq;
using QuestionaireOrchestration.Models;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionaireOrchestration.QueryServices
{
    internal sealed class QuestionnaireOutputModelQueryService :
        ModelQueryServiceBase<QuestionnaireOutputModel>,
        IQuestionnaireOutputModelQueryService
    {
        private readonly IQuestionOutputModelQueryService m_questionQueryService;

        public QuestionnaireOutputModelQueryService(
            IDomainItemLocator domainItemLocator,
            IQuestionOutputModelQueryService questionQueryService)
            : base(domainItemLocator)
        {
            m_questionQueryService = questionQueryService;
        }

        public override IEnumerable<QuestionnaireOutputModel> GetAll()
        {
            return m_domainItemLocator
                .GetAll<IQuestionnaireOutputItem>()
                .Select(x => new QuestionnaireOutputModel(x.Id, x.DisplayName));
        }

        public QuestionnaireModel GetModel(Guid id)
        {
            var questionnaire = m_domainItemLocator
                .Get<IQuestionnaireOutputItem>(id);

            var questions = questionnaire
                .Questions
                .Select(x => m_questionQueryService.GetQuestionModel(x.Id))
                .ToList();

            var questionnaireModel = new QuestionnaireModel(id, questionnaire.DisplayName);
            foreach (var question in questions)
            {
                questionnaireModel.Questions.Add(question);
            }

            return questionnaireModel;
        }
    }
}