using System;

namespace QuestionaireOrchestration.Models
{
    public abstract class DomainItemModel
    {
        protected DomainItemModel(Guid id, string name)
        {
            Id = id;
            Name = name;
        }

        public Guid Id { get; }

        public string Name { get; }

        public override bool Equals(object obj)
        {
            var model = obj as DomainItemModel;
            return model != null && this == model;
        }

        public override int GetHashCode()
        {
            return Id.GetHashCode();
        }

        public override string ToString()
        {
            return Name;
        }

        protected bool Equals(DomainItemModel other)
        {
            return other.Id == Id;
        }
        
        //public static implicit operator Reference(DomainItemModel model);
    }
}
