using System.Collections.Generic;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public abstract class AstNode
    {
        protected AstNode()
        {
            Children = new ChildCollection(this);
        }

        public LineInfo Line { get; set; }

        public AstNode Parent { get; set; }

        public ChildCollection Children { get; }

        public abstract void Accept(IAstVisitor visitor);

        public IList<T> Descendants<T>() where T : AstNode
        {
            var list = new List<T>();
            Descendants(list);
            return list;
        }

        private void Descendants<T>(ICollection<T> matches) where T : AstNode
        {
            if (typeof(T) == GetType())
            {
                matches.Add(this as T);
            }

            foreach (var child in Children)
            {
                child.Descendants(matches);
            }
        }
    }
}