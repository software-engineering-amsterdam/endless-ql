using System;
using QuestionaireDomain.Entities.API;

namespace QuestionnaireDomain.Logic.API
{
    public interface IQuestionnaireCreator
    {
        Guid Create(string definition);
    }
}
