namespace QuestionnaireInfrastructure.API
{
    public interface ICommandHandler<in TCommand> where TCommand : ICommandMessage
    {
        void Execute(TCommand command);
    }
}