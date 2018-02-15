using QuestionaireDomain.Entities.API;

namespace QuestionnaireDomain.Logic.API
{
    public interface IQuestionnaireCreator
    {
        IQuestionnaireAst Create(string definition);
    }
}
