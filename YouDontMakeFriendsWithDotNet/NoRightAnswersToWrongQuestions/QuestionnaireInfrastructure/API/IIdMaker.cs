using System;

namespace QuestionnaireInfrastructure.API
{
    public interface IIdMaker
    {
        Guid Next { get; }
    }
}
