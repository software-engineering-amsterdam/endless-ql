using System;
using System.Collections.Generic;
using QuestionaireOrchestration.Models;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.Commands
{
    public class UpdateValuesCommand : ICommandMessage
    {
        public QuestionnaireModel Questionnaire;
    }
}
