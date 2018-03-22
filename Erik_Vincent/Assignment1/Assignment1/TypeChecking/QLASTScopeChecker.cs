using System;
using System.Collections.Generic;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.TypeChecking
{
    public class QLASTScopeChecker : QLASTBaseVisitor
    {
        public static void CheckReferenceScopes(QuestionForm questionForm)
        {
            var checker = new QLASTScopeChecker();
            checker.Visit(questionForm);
        }

        private QLASTScopeChecker() { }

        private static void ReportError(string error)
        {
            Console.WriteLine(error);
        }

        public override void Visit(Reference expression)
        {
            try
            {
                base.Visit(expression);
            }
            catch (UndeclaredQuestionException)
            {
                ReportError(expression.QuestionId + " not declared in this scope.");
            }
            catch (InvalidExpressionException)
            {
                ReportError(expression.QuestionId + " contains an invalid reference.");
            }
        }
    }
}