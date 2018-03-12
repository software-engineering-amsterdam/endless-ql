using System;
using System.Collections.Generic;
using System.Linq;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
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
