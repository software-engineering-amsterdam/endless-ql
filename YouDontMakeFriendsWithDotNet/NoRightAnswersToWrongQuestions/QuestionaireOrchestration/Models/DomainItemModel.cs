using System;
using System.Collections.Generic;

namespace QuestionnaireOrchestration.Models
{
    public abstract class DomainItemModel : IEqualityComparer<DomainItemModel>
    {
        protected DomainItemModel(Guid id, string name)
        {
            Id = id;
            Name = name;
        }

        public Guid Id { get; }

        public string Name { get; }

        public bool Equals(DomainItemModel x, DomainItemModel y)
        {
            return x != null && y != null && x.Id == y.Id;
        }

        public int GetHashCode(DomainItemModel obj)
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
    }
}