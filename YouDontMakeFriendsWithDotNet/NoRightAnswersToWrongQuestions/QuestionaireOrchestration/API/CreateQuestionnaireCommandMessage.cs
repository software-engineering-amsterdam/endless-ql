using System;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.API
{
    public class CreateQuestionnaireCommandMessage : ICommandMessage
    {
        public CreateQuestionnaireCommandMessage()
        {
            Id = Guid.NewGuid();
        }

        public Guid Id { get; }

        public string Text { get; set; }
    }
}
