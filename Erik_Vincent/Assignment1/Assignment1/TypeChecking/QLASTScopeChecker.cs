using System;
using System.Collections.Generic;
using System.Linq;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.TypeChecking
{
    public class QLASTScopeChecker : QLASTBaseVisitor
    {
        private readonly MessageContainer _messages = new MessageContainer();

        public static (IEnumerable<string> errors, IEnumerable<string> warnings) CheckReferenceScopes(QuestionForm questionForm)
        {
            var checker = new QLASTScopeChecker();
            checker.Visit(questionForm);
            return checker._messages.ToTuple();
        }

        private QLASTScopeChecker() { }

        public override void Visit(Reference expression)
        {
            if (!QuestionsInScope.Contains(expression.QuestionId))
            {
                _messages.AddError(expression.QuestionId + " not declared in this scope.");
            }
        }
    }
}