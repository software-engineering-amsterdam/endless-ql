using System.Collections.Generic;
using System.Linq;

namespace Assignment1.Model.QLS.AST
{
    public class StyleSheet : IQLSASTNode
    {
        public IReadOnlyCollection<Page> Pages => _pages.AsReadOnly();

        private readonly List<Page> _pages;

        public StyleSheet(IEnumerable<Page> pages)
        {
            _pages = pages.ToList();
        }

        public void Accept(IQLSASTVisitor visitor) => visitor.Visit(this);
    }
}
