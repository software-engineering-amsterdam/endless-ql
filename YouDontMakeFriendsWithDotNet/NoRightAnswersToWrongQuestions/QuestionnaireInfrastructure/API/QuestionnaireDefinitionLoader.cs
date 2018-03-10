using System;
using System.Collections.Generic;

namespace QuestionnaireInfrastructure.API
{
    internal class QuestionnaireDefinitionLoader : IQuestionnaireDefinitionLoader
    {
        public IEnumerable<string> Load(string filePath = null)
        {
            if (string.IsNullOrEmpty(filePath))
            {
                filePath = GetLocalPath();
            }
            //ToDo: return propper file
            var file = @"
form personalInfo {
  name: ""Name: ""  string
            dateOfBirth: ""D.O.B."" date
            childCount: ""number of children:"" integer
            currentBlance: ""money in pocket:"" decimal
            isHappy: ""are you happy:"" boolean
            if (isHappy && (currentBlance < 50))
            {
                shouldNotBeHappy: ""Are you Sure you're happy?"" boolean
            }
            if (!isHappy && (currentBalance >= 50))
            {
                shouldBeHappy: ""Are you Sure you're unhappy?"" boolean
            }
        }
";
            return new List<string> { file};
        }

        private string GetLocalPath()
        {
            //ToDo: get local file (example1.ql)
            return "ToBeReplaced";

        }
    }
}