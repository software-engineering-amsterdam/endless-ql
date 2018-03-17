using System.Collections.Generic;

namespace Assignment1.Model.QL.AST
{
    public class QuestionForm : IQLASTNode
    {
        public IReadOnlyCollection<Statement> Statements => _statements.AsReadOnly();

        private readonly List<Statement> _statements;

        public QuestionForm(List<Statement> statements)
        {
            _statements = statements;
        }

        public void Accept(IQLASTVisitor visitor) => visitor.Visit(this);
    }
}
