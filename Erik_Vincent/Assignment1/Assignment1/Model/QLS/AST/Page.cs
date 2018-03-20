using System.Collections.Generic;

namespace Assignment1.Model.QLS.AST
{
    public class Page : IQLSASTNode
    {
        public string Id { get; }
        public IReadOnlyCollection<Statement> Statements => _statements.AsReadOnly();

        private readonly List<Statement> _statements;

        public Page(string id, List<Statement> statements)
        {
            Id = id;
            _statements = statements;
        }

        public void Accept(IQLSASTVisitor visitor) => visitor.Visit(this);
    }
}
