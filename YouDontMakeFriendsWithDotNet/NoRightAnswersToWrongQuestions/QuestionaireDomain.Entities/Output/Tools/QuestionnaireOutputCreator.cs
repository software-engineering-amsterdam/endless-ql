using System;
using System.Collections.Generic;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    internal class QuestionnaireOutputCreator : IQuestionnaireOutputCreator
    {
        private readonly IBuildOutputVisitor m_buildOutputVisitor;
        private readonly IQuestionnaireOutputUpdater m_outputUpdater;

        public QuestionnaireOutputCreator(
            IBuildOutputVisitor buildOutputVisitor,
            IQuestionnaireOutputUpdater outputUpdater)
        {
            m_buildOutputVisitor = buildOutputVisitor;
            m_outputUpdater = outputUpdater;
        }
        
        public void CreateOrUpdate(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            //if (m_outputUpdater.OutputExistsFor(questionnaireRootNode))
            //{
            //    m_outputUpdater.UpdateOutputFor(questionnaireRootNode);
            //}
            //else
            //{
                m_buildOutputVisitor.Build(questionnaireRootNode);
            //}
        }
    }
}
