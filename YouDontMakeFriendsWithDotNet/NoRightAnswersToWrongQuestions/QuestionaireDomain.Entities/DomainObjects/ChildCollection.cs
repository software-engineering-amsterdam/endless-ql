using System.Collections.ObjectModel;

namespace QuestionaireDomain.Entities.DomainObjects
{
    public class ChildCollection : ObservableCollection<AstNode>
    {
        private readonly AstNode m_parent;

        public ChildCollection(AstNode parent)
        {
            m_parent = parent;
        }

        protected override void InsertItem(int index, AstNode item)
        {
            item.Parent = m_parent;
            base.InsertItem(index, item);
        }
    }
}