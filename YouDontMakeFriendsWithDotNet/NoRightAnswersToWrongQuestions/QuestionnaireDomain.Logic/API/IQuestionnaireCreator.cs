using QuestionaireDomain.Entities.API;

namespace QuestionnaireDomain.Logic.API
{
    public interface IQuestionnaireCreator
    {
        IQuestionnaire Create(string definition);
    }
}
