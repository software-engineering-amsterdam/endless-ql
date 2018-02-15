using System;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.API
{
    public class ParseTextCommandMessage : ICommandMessage
    {
        public ParseTextCommandMessage()
        {
            Id = Guid.NewGuid();
        }

        public Guid Id { get; }

        public string Text { get; set; }
    }
}
