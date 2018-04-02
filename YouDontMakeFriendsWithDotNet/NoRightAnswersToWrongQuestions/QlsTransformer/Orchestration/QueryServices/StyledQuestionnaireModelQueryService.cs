using System;
using System.Collections.Generic;
using System.Linq;
using QlsTransformer.Domain.Output.Nodes;
using QlsTransformer.Orchestration.Models;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireOrchestration.Models;
using QuestionnaireOrchestration.QueryServices;

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
            return DomainItemLocator
                .GetAll<IStyledQuestionnaireOutputItem>()
                .Select(x => new StyledQuestionnaireModel(x.Id, x.DisplayName));
        }

        public StyledQuestionnaireModel GetModel(Guid firstItemId)
        {
            var domainItem = DomainItemLocator
                .Get<IStyledQuestionnaireOutputItem>(firstItemId);


            var model = new StyledQuestionnaireModel(
                domainItem.Id,
                domainItem.DisplayName);

            foreach (var pageId in domainItem.Pages)
            {
                var pageModel = CreatePageModel(pageId);
                model.Pages.Add(pageModel);
            }

            return model;
        }

        private PageModel CreatePageModel(DomainId<IPagesOutputItem> pageId)
        {
            var pageItem = pageId.ToDomainItem(DomainItemLocator);
            var pageModel = new PageModel(pageItem.Id, pageItem.DisplayName);
            foreach (var sectionId in pageItem.Sections)
            {
                var sectionModel = CreateSectionModel(sectionId);
                pageModel.Sections.Add(sectionModel);
            }

            return pageModel;
        }

        private SectionModel CreateSectionModel(DomainId<ISectionOutputItem> sectionId)
        {
            var sectionItem = sectionId.ToDomainItem(DomainItemLocator);
            var sectionModel = new SectionModel(sectionItem.Id, sectionItem.DisplayName);
            foreach (var questionId in sectionItem.Questions)
            {
                var questionModel = CreateQuestionModel(questionId);
                sectionModel.Questions.Add(questionModel);
            }

            return sectionModel;
        }

        private StyledQuestionModel CreateQuestionModel(
            DomainId<IStyledQuestionOutputItem> questionId)
        {
            var styledQuestion = questionId.ToDomainItem(DomainItemLocator);

            var questionModel = new QuestionModel(
                styledQuestion.Id,
                styledQuestion.Variable.Id,
                styledQuestion.QuestionText,
                styledQuestion.Visible,
                styledQuestion.ReadOnly,
                styledQuestion.QuestionType);

            var styleModel = new StyleModel(
                styledQuestion.Widget,
                styledQuestion.Width,
                styledQuestion.Font,
                styledQuestion.FontSize,
                styledQuestion.Color);

            return new StyledQuestionModel(questionModel, styleModel);
        }
    }
}