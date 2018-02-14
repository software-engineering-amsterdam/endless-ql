namespace QuestionnaireInfrastructure.API
{
    public class AppSettings
    {
        public AppSettings(
            bool loadDefinitionFromFile = false,
            string definitionFileLocation = "")
        {
            LoadFromFile = loadDefinitionFromFile;
            FileLocation = definitionFileLocation;
        }

        public bool LoadFromFile { get; }
        public string FileLocation { get; }
    }
}
