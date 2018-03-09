using System;
using QuestionaireDomain.Entities.API.Output;

namespace QuestionaireOrchestration.API
{
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