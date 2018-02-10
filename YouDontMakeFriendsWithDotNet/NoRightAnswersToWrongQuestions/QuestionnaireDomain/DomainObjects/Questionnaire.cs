using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using QuestionnaireDomain.API;

namespace QuestionnaireDomain.DomainObjects
{
    internal class Questionnaire : IQuestionnaire
    {
        public string FormName { get; set; }
    }
}
