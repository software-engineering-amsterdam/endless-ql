namespace QuestionnaireDomain.API
{
    public interface IQuestionnaireCreator
    {
        IQuestionnaire Create(string definition);
    }
}
