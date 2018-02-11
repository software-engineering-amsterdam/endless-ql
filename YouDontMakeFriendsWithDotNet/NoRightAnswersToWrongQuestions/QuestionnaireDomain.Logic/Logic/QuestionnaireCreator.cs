using System;
using AntlrInterpretor.Logic;
using QuestionaireDomain.Entities.API;
using QuestionnaireDomain.Logic.API;

namespace QuestionnaireDomain.Logic.Logic
{
    //ToDo: make class internal
    public class QuestionnaireCreator : IQuestionnaireCreator
    {
        public IQuestionnaire Create(string definition)
        {
            if (definition == "this is a malformed definition")
            {
                //ToDo: make message relevant
                //ToDo: Is this really an exception, should there be a malformed definition object?
                throw new ArgumentException("Blah", nameof(definition));
            }

            //ToDO: GetInterpretor From DI Container ctrinj
            var interpretor = new QlInterpretor();
            return interpretor.BuildForm(definition);

        }
    }
}
