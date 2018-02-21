using System;

namespace QuestionnaireInfrastructure.API
{
    public interface ICommandMessage
    {
        Guid Id { get; }
    }
}