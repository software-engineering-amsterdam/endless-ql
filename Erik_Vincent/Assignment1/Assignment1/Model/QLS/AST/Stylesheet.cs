using System.Collections.Generic;

namespace Assignment1.Model.QLS.AST
{
    public class StyleSheet : IQLSASTNode
    {
        public IReadOnlyCollection<Page> Pages => _pages.AsReadOnly();

        private readonly List<Page> _pages;

        public StyleSheet(List<Page> pages)
        {
            _pages = pages;
        }

        public void Accept(IQLSASTVisitor visitor) => visitor.Visit(this);
    }
}
