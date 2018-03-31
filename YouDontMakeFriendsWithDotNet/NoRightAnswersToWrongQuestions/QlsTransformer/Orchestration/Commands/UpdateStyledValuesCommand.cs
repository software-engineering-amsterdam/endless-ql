using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QlsTransformer.Orchestration.Models;
using QuestionnaireInfrastructure.API;

namespace QlsTransformer.Orchestration.Commands
{
    public class UpdateStyledValuesCommand : ICommandMessage
    {
        public StyledQuestionnaireModel Questionnaire { get; set; }
    }
}
