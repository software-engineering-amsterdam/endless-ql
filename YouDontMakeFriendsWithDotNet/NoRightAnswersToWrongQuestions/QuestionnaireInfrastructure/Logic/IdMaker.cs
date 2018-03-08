using System;
using QuestionnaireInfrastructure.API;

namespace QuestionnaireInfrastructure.Logic
{
    internal class IdMaker : IIdMaker
    {
        public Guid Next => Guid.NewGuid();
    }
}
