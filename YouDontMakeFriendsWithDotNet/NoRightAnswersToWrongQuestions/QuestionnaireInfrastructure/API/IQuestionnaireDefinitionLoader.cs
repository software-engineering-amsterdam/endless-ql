namespace QuestionnaireInfrastructure.API
{
    public interface IQuestionnaireDefinitionLoader
    {
        string Load();
        string Load(string filePath);
    }
}
