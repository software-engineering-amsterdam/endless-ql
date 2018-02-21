using System;

namespace QuestionnaireDomain.Logic.API
{
    public interface IQuestionnaireCreator
    {
        Guid Create(string definition);
    }
}