using System;
using System.Collections.Generic;
using System.Linq;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Output.Tools.Interfaces;

namespace QuestionnaireDomain.Entities.Output.Tools
{
    public class QuestionnaireModelCreator : IQuestionnaireModelCreator
    {
        private readonly IBuildOutputVisitor m_buildOutputVisitor;

        public QuestionnaireModelCreator(IBuildOutputVisitor buildOutputVisitor)
        {
            m_buildOutputVisitor = buildOutputVisitor;
        }

        public bool Validate(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            //ToDo Make this work
            return true;
        }

        public void Create(Reference<IQuestionnaireRootNode> questionnaireRootNode)
        {
            m_buildOutputVisitor.Build(questionnaireRootNode);
        }

    }
}
