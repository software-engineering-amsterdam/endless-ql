namespace QuestionnaireInfrastructure.API
{
    public interface ICommandBus
    {
        void Send<T>(T command) where T : ICommandMessage;
    }
}