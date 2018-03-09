using System;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.API
{
    public interface IQuestionnaireAstCreator
    {
        Reference<IQuestionnaireRootNode> Create(string definition);
    }
}