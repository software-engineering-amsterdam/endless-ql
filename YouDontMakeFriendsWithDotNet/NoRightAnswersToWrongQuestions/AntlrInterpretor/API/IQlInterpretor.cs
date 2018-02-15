using QuestionaireDomain.Entities.API;

namespace AntlrInterpretor.API
{
    public interface IQlInterpretor
    {
        IQuestionnaire BuildForm(string definition);
    }
}