using System;
using QuestionnaireDomain.Entities.Output.Nodes.Interfaces;

namespace QuestionnaireOrchestration.Models
{
    // ToDo: do I need to use this Type parameter somewhere? or is it just a degenerate marker
    public class ModelReference<T> where T : IOutputItem
    {
        public ModelReference(Guid id, string displayValue)
        {
            Id = id;
            DisplayValue = displayValue;
        }
        public Guid Id { get; }
        public string DisplayValue { get; }
    }
}