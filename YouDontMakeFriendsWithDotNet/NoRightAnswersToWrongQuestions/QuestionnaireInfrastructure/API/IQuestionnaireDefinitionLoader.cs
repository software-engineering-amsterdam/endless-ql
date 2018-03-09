using System.Collections.Generic;

namespace QuestionnaireInfrastructure.API
{
    public interface IQuestionnaireDefinitionLoader
    {
        IEnumerable<string> Load(string filePath = null);
    }
}
