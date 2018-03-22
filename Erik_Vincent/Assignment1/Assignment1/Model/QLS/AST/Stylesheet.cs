using Assignment1.Model.QL.AST;
using System.Collections.Generic;
using System.Linq;

namespace Assignment1.Model.QLS.AST
{
    public class StyleSheet : ASTNode, IQLSASTNode
    {
        public IReadOnlyCollection<Page> Pages => _pages.AsReadOnly();

        private readonly List<Page> _pages;

        public StyleSheet(int lineNumber, IEnumerable<Page> pages)
        {
            _lineNumber = lineNumber;
            _pages = pages.ToList();
        }

        public void Accept(IQLSASTVisitor visitor) => visitor.Visit(this);
    }
}
