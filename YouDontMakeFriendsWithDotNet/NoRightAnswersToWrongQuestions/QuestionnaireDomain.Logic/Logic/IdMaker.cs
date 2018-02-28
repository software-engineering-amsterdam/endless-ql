using System;
using QuestionaireDomain.Entities.API;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class IdMaker : IIdMaker
    {
        public Guid Next => Guid.NewGuid();
    }
}
