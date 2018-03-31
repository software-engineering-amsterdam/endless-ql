using Assignment1.Model.QL.AST.Expression;
using Assignment1.Model.QL.RenderTree;
using System;
using System.Collections.Generic;

namespace Assignment1.Rendering
{
    public class SymbolTable
    {
        private Dictionary<string, IExpression> _table = new Dictionary<string, IExpression>();

        public IExpression GetExpression(string identifier) => _table.ContainsKey(identifier) ? _table[identifier] : throw new Exception();

        public void SetExpression(string identifier, IExpression expression)
        {
            if (_table.ContainsKey(identifier))
            {
                _table[identifier] = expression;
            } else
            {
                _table.Add(identifier, expression);
            }
        }

        public void RegisterQuestions(IEnumerable<RenderableQuestion> questions)
        {
            foreach (RenderableQuestion question in questions)
            {
                SetExpression(question.Id, question.Computation);
            }
        }
    }
}
