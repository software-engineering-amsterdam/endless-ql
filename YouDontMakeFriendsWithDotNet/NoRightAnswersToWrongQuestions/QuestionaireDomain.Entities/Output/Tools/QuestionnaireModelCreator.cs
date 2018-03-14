using System;
using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    public interface IQuestionnaireModelUpdater
    {
        bool ModelExistsFor(Reference<IQuestionnaireRootNode> questionnaireRootNode);
        void DeleteModelFor(Reference<IQuestionnaireRootNode> questionnaireRootNode);
    }

    internal class QuestionnaireModelUpdater : IQuestionnaireModelUpdater
    {
        private readonly IDomainItemLocator m_domainItemLocator;

        public QuestionnaireModelUpdater(
            IDomainItemLocator domainItemLocator)
        {
            m_domainItemLocator = domainItemLocator;
        }

        public bool ModelExistsFor(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            var name = questionnaireRootNode.ToDomainItem(m_domainItemLocator).QuestionnaireName;

            return m_domainItemLocator
                .GetAll<IQuestionnaireOutputItem>()
                .Any(x => x.DisplayName == name);
        }

        public void DeleteModelFor(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            throw new NotImplementedException();
        }
    }

    internal class QuestionnaireModelCreator : IQuestionnaireModelCreator
    {
        private readonly IBuildOutputVisitor m_buildOutputVisitor;
        private readonly IQuestionnaireModelUpdater m_modelUpdater;

        public QuestionnaireModelCreator(
            IBuildOutputVisitor buildOutputVisitor,
            IQuestionnaireModelUpdater modelUpdater)
        {
            m_buildOutputVisitor = buildOutputVisitor;
            m_modelUpdater = modelUpdater;
        }

        public bool Validate(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            //ToDo Make this work
            return true;
        }

        public void Create(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            if (m_modelUpdater.ModelExistsFor(questionnaireRootNode))
            {
                m_modelUpdater.DeleteModelFor(questionnaireRootNode);
            }

            m_buildOutputVisitor.Build(questionnaireRootNode);
        }
    }
}
