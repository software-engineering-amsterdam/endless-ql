using System;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;

namespace QuestionnaireDomain.Entities.Ast.Tools
{
    internal class QuestionnaireAstCreator : IQuestionnaireAstCreator
    {
        private readonly IAstTreeBuilder m_astTreeBuilder;

        public QuestionnaireAstCreator(IAstTreeBuilder astTreeBuilder)
        {
            m_astTreeBuilder = astTreeBuilder;
        }

        public Reference<IQuestionnaireRootNode> Create(string definition)
        {
            return m_astTreeBuilder.BuildForm(definition);
        }
    }
}