using System;
using System.Collections.Generic;
using System.Linq;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Assignment1.Model.QL;
using Assignment1.Parser;

namespace Assignment1.TypeChecking
{
    internal class QLTypeChecker : QLBaseListener
    {
        private QuestionForm _form;
        private readonly Dictionary<string, Question> _questions = new Dictionary<string, Question>();
        private readonly Dictionary<string,string> _warnings = new Dictionary<string, string>(); // TODO: move to errorhandler
        private QLParseErrorHandler _errorHandler = new QLParseErrorHandler();

        /// <summary>
        /// Checks if the Id already exists and if value is assigned, check if the type is correct.
        /// </summary>
        /// <param name="question">The question to check.</param>
        public List<string> TypeCheckQuestion(Question question)
        {
            List<string> errors = new List<string>();
            string questionId = question.Id;

            if (QuestionIdExists(questionId))
            {
                errors.Add("The question id '" + questionId + "' already exists in the current context.");
            }
            if (question.Computed)
            {
                Expression computation = question.Computation;
                errors = TypeCheckExpressionToQuestion(question, computation, errors);
            }
            else
            {
                errors = TypeCheckValueToQuestion(question, errors);
            }
            return errors;
        }

        // TODO: find cleaner way to do this
        private List<string> TypeCheckValueToQuestion(Question question, List<string> currentErrors)
        {
            if (question is QuestionBool)
            {
                if (!(question.Value is bool))
                {
                    currentErrors.Add("Value is not of type bool.");
                }
            }
            if (question is QuestionDecimal || question is QuestionMoney)
            {
                if (!(question.Value is decimal))
                {
                    currentErrors.Add("Value is not of type decimal.");
                }
            }
            if (question is QuestionInt)
            {
                if (!(question.Value is int))
                {
                    currentErrors.Add("Value is not of type int.");
                }
            }
            if (question is QuestionString)
            {
                if (!(question.Value is string))
                {
                    currentErrors.Add("Value is not of type string.");
                }
            }
            if (question is QuestionDate)
            {
                if (!(question.Value is DateTime))
                {
                    currentErrors.Add("Value is not of type date.");
                }
            }
            return currentErrors;
        }

        // TODO: find cleaner way to do this
        private List<string> TypeCheckExpressionToQuestion(Question question, Expression computation, List<string> currentErrors)
        {
            if (question is QuestionBool)
            {
                if (!(computation.Evaluate() is bool))
                {
                    currentErrors.Add("Expression does not evaluate to bool.");
                }
            }
            if (question is QuestionDecimal || question is QuestionMoney)
            {
                if (!(computation.Evaluate() is decimal))
                {
                    currentErrors.Add("Expression does not evaluate to decimal.");
                }
            }
            if (question is QuestionInt)
            {
                if (!(computation.Evaluate() is int))
                {
                    currentErrors.Add("Expression does not evaluate to int.");
                }
            }
            if (question is QuestionString)
            {
                if (!(computation.Evaluate() is string))
                {
                    currentErrors.Add("Expression does not evaluate to string.");
                }
            }
            if (question is QuestionDate)
            {
                if (!(computation.Evaluate() is DateTime))
                {
                    currentErrors.Add("Expression does not evaluate to date.");
                }
            }
            return currentErrors;
        }

        private bool QuestionIdExists(string questionId) => _questions.ContainsKey(questionId);

        public bool QuestionLabelExists(string questionLabel)
        {
            List<Question> questionList = _questions.Values.ToList();
            foreach (Question questionItem in questionList)
            {
                if (questionItem.Label.Equals(questionLabel))
                    return true;
            }
            return false;
        }

        private void AddWarning(ParserRuleContext context, string questionId, string message)
        {
            _warnings.Add(questionId, "Line " + context.Start.Line + ": " + message);
        }

        public override void ExitForm(QL.FormContext context)
        {
            foreach (string warning in _warnings.Values)
            {
                Console.WriteLine(warning);
            }
            _form = context.result;
            _form.Warnings = _warnings;
        }

        /* Check for each question if the label is already used and add a warning if this is the case.
         */
        public override void EnterQuestion(QL.QuestionContext context)
        {
            string questionLabel = context.result.Label;
            string questionId = context.result.Id;

            if (QuestionLabelExists(questionLabel))
            {
                AddWarning(context, questionId, "The question label '" + questionLabel + "' has already been used.");
            }
        }

        /* Check for each question if the id already exists and add an error if this is the case.
         */
        public override void ExitQuestion(QL.QuestionContext context)
        {
            string questionId = context.result.Id;

            List<string> errors = TypeCheckQuestion(context.result);
            if (errors.Count > 0)
            {
                foreach (string error in errors)
                {
                    _errorHandler.AddError(context.Start.Line, error);
                }
            }
            _questions.Add(questionId, context.result);
        }

        /* Check for each if statement if the expression in the condition is of type boolean.
         */
        public override void ExitIfstatement(QL.IfstatementContext context)
        {
            object conditionType = context._expression.result.Evaluate();
            if (!(conditionType is bool))
            {
                _errorHandler.AddError(context.Start.Line, "The expression '" + context._expression.GetText() + "' is not of type boolean.");
            }
        }

        /* Check for each expression if the left and right operands are of the correct type.
         * For example, for an arithmetic expression the left and right operands should be numeric.
         */
        public override void ExitExpression(QL.ExpressionContext context)
        {
            Expression expression = context.result;
            try
            {
                var expressionType = expression.Evaluate();
            }
            catch (Exception exception)
            {
                _errorHandler.AddError(context.Start.Line, exception.Message);
            }
        }

        /* Check for each expressionId if the referenced questionId exists. Adds an error message
         * if this is not the case.
         */
        public override void ExitExpressionId(QL.ExpressionIdContext context)
        {
            try
            {
                context.result.Question = _questions[context.result.Id];
            }
            catch (KeyNotFoundException)
            {
                _errorHandler.AddError(context.Start.Line, "The question id '" + context.result.Id + "' does not exist in the current context.");
            }
        }

        internal static QuestionForm ParseString(string input)
        {
            QLTypeChecker listener = new QLTypeChecker();
            QLErrorListener errorListener = new QLErrorListener(listener._errorHandler);

            ICharStream stream = CharStreams.fromstring(input);
            var lexer = new QLLexer(stream);
            lexer.RemoveErrorListeners();
            lexer.AddErrorListener(errorListener);
            ITokenStream tokens = new CommonTokenStream(lexer);
            QL parser = new QL(tokens);
            parser.RemoveErrorListeners();
            parser.AddErrorListener(errorListener);
            QL.FormContext context = parser.form();
            
            ParseTreeWalker walker = new ParseTreeWalker();
            walker.Walk(listener, context);

            if (listener._errorHandler.FormHasErrors)
                listener._errorHandler.ThrowQLParseException();
            return listener._form;
        }
    }
}
