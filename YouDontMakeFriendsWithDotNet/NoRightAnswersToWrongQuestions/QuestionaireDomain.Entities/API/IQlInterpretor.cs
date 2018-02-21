namespace QuestionaireDomain.Entities.API
{
    public interface IQlInterpretor
    {
        IQuestionnaireAst BuildForm(string definition);
    }
}
