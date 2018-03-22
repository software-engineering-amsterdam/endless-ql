using Assignment1.Model.QL.AST;
using System.Collections.Generic;
using System.Linq;

namespace Assignment1.Model.QLS.AST
{
    public class Page : ASTNode, IQLSASTNode
    {
        public string Id { get; }
        public IReadOnlyCollection<Statement> Statements => _statements.AsReadOnly();

        private readonly List<Statement> _statements;

        public Page(int lineNumber, string id, IEnumerable<Statement> statements)
        {
            _lineNumber = lineNumber;
            Id = id;
            _statements = statements.ToList();
        }

        public void Accept(IQLSASTVisitor visitor) => visitor.Visit(this);
    }
}
