using System.Collections.Generic;

namespace Assignment1.Model.QL.AST
{
    public class QuestionForm : IQLASTNode
    {
        public string Id { get; }
        public IReadOnlyCollection<Statement> Statements => _statements.AsReadOnly();

        private readonly List<Statement> _statements;

        public QuestionForm(string id, List<Statement> statements)
        {
            Id = id;
            _statements = statements;
        }

        public void Accept(IQLASTVisitor visitor) => visitor.Visit(this);
    }
}
