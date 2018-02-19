namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionnaireAst : IDomainItem
    {
        string FormName { get; set; }
    }
}