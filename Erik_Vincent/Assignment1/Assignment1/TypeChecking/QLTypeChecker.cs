using System;
using System.Collections.Generic;
using System.Linq;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using Assignment1.Model.QL;
using Assignment1.Parser;

namespace Assignment1.TypeChecking
{
    internal class QLTypeChecker : QLBaseListener, IQuestionVisitor
    {
        private QuestionForm _form;
        private readonly Dictionary<string, Question> _questions = new Dictionary<string, Question>();
        private readonly List<string> _warnings = new List<string>();
        private QLParseErrorHandler _errorHandler = new QLParseErrorHandler();
        private int currentLineNumber = 0;

        #region Type checking functions
        
        private void TypeCheckQuestionId(string questionId)
        {
            if (QuestionIdExists(questionId))
            {
                _errorHandler.AddError(currentLineNumber, "The question id '" + questionId + "' already exists in the current context.");
            } 
        }

        private void TypeCheckQuestionLabel(string questionLabel)
        {
            if (QuestionLabelExists(questionLabel))
                _warnings.Add("Line " + currentLineNumber + ": The question label '" + questionLabel + "' has already been used.");
        }

        private void TypeCheckQuestionBool(QuestionBool question)
        {
            string questionId = question.Id;
            TypeCheckQuestionId(questionId);
            TypeCheckQuestionLabel(question.Label);
            if (question.Computed)
            {
                if (!(question.Computation.Evaluate() is bool))
                    _errorHandler.AddError(currentLineNumber, "The expression does not evaluate to bool.");
            } else
            {
                if (!(question.Value is bool))
                    _errorHandler.AddError(currentLineNumber, "The value is not of type bool.");
            }
        }

        private void TypeCheckQuestionInt(QuestionInt question)
        {
            string questionId = question.Id;
            TypeCheckQuestionId(questionId);
            TypeCheckQuestionLabel(question.Label);
            if (question.Computed)
            {
                if (!(question.Computation.Evaluate() is int))
                    _errorHandler.AddError(currentLineNumber, "The expression does not evaluate to int.");
            }
            else
            {
                if (!(question.Value is int))
                    _errorHandler.AddError(currentLineNumber, "The value is not of type int.");
            }
        }

        private void TypeCheckQuestionDecimal(QuestionDecimal question)
        {
            string questionId = question.Id;
            TypeCheckQuestionId(questionId);
            TypeCheckQuestionLabel(question.Label);
            if (question.Computed)
            {
                if (!(question.Computation.Evaluate() is decimal))
                    _errorHandler.AddError(currentLineNumber, "The expression does not evaluate to decimal.");
            }
            else
            {
                if (!(question.Value is decimal))
                    _errorHandler.AddError(currentLineNumber, "The value is not of type decimal.");
            }
        }

        private void TypeCheckQuestionDecimal(QuestionMoney question)
        {
            string questionId = question.Id;
            TypeCheckQuestionId(questionId);
            TypeCheckQuestionLabel(question.Label);
            if (question.Computed)
            {
                if (!(question.Computation.Evaluate() is decimal))
                    _errorHandler.AddError(currentLineNumber, "The expression does not evaluate to decimal.");
            }
            else
            {
                if (!(question.Value is decimal))
                    _errorHandler.AddError(currentLineNumber, "The value is not of type decimal.");
            }
        }

        private void TypeCheckQuestionString(QuestionString question)
        {
            string questionId = question.Id;
            TypeCheckQuestionId(questionId);
            TypeCheckQuestionLabel(question.Label);
            if (question.Computed)
            {
                if (!(question.Computation.Evaluate() is string))
                    _errorHandler.AddError(currentLineNumber, "The expression does not evaluate to string.");
            }
            else
            {
                if (!(question.Value is string))
                    _errorHandler.AddError(currentLineNumber, "The value is not of type string.");
            }
        }

        private void TypeCheckQuestionDate(QuestionDate question)
        {
            string questionId = question.Id;
            TypeCheckQuestionId(questionId);
            TypeCheckQuestionLabel(question.Label);
            if (question.Computed)
            {
                if (!(question.Computation.Evaluate() is DateTime))
                    _errorHandler.AddError(currentLineNumber, "The expression does not evaluate to date.");
            }
            else
            {
                if (!(question.Value is DateTime))
                    _errorHandler.AddError(currentLineNumber, "The value is not of type date.");
            }
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

        #endregion

        #region Listener implementation

        public override void ExitForm(QL.FormContext context)
        {
            foreach (string warning in _warnings)
            {
                Console.WriteLine(warning);
            }
            _form = context.result;
            _form.Warnings = _warnings;
        }

        /* Check for each question if the id already exists and add an error if this is the case.
         */
        public override void ExitQuestion(QL.QuestionContext context)
        {
            currentLineNumber = context.Start.Line;
            string questionId = context.result.Id;

            context.result.Accept(this);
            _questions.Add(questionId, context.result);
        }

        /* Check for each if statement if the expression in the condition is of type boolean.
         */
        public override void ExitIfstatement(QL.IfstatementContext context)
        {
            object conditionType = context._expression.result.Evaluate();
            if (!(conditionType is bool))
            {
                _errorHandler.AddError(context.Start.Line, "The expression '" + context._expression.GetText() + "' in the if statement is not of type boolean.");
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

        #endregion

        #region Visitor implementation

        public void Visit(QuestionBool question)
        {
            TypeCheckQuestionBool(question);
        }

        public void Visit(QuestionInt question)
        {
            TypeCheckQuestionInt(question);
        }

        public void Visit(QuestionDate question)
        {
            TypeCheckQuestionDate(question);
        }

        public void Visit(QuestionDecimal question)
        {
            TypeCheckQuestionDecimal(question);
        }

        public void Visit(QuestionMoney question)
        {
            TypeCheckQuestionDecimal(question);
        }

        public void Visit(QuestionString question)
        {
            TypeCheckQuestionString(question);
        }

        #endregion

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
