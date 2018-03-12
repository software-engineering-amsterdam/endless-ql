using System;
using QuestionaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.API
{
    public interface IQuestionnaireAstCreator
    {
        Reference<IQuestionnaireRootNode> Create(string definition);
    }
}