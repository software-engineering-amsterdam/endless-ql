using System;

namespace QuestionaireDomain.Entities.API
{
    public interface IIdMaker
    {
        Guid Next { get; }
    }
}
