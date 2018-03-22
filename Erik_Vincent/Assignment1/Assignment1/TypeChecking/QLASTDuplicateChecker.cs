using System;
using Assignment1.Model.QL.AST;
using Assignment1.Model.QL.AST.Expression;

namespace Assignment1.TypeChecking
{
    public class QLASTDuplicateChecker : QLASTBaseVisitor
    {
        public static void CheckDuplicates(QuestionForm questionForm)
        {
            var checker = new QLASTDuplicateChecker();
            try
            {
                checker.Visit(questionForm);
            }
            catch (DuplicateQuestionException e)
            {
                Console.WriteLine("Error found on line " + e.Question.LineNumber + ": " + e.Question.Id + " was already declared in this context.");
            }
        }

        private QLASTDuplicateChecker() { }
    }
}
