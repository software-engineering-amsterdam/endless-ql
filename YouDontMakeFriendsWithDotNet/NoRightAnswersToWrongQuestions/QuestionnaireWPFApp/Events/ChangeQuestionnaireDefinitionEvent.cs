using System;
using Prism.Events;

namespace QuestionnaireWPFApp.Events
{
    public class ChangeQuestionnaireDefinitionEvent : PubSubEvent<Guid>
    {
    }
}