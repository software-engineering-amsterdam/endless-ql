using System.Collections.Generic;
using System.Linq;

namespace Assignment1.Model.QL.AST
{
    public class QuestionForm : QLNode, IQLASTNode
    {
        public string Id { get; }
        public IReadOnlyCollection<Statement> Statements => _statements.AsReadOnly();

        private readonly List<Statement> _statements;

        public QuestionForm(int lineNumber, string id, IEnumerable<Statement> statements)
        {
            _lineNumber = lineNumber;
            Id = id;
            _statements = statements.ToList();
        }

        public void Accept(IQLASTVisitor visitor) => visitor.Visit(this);
    }
}
