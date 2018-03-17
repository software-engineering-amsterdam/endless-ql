using System;
using System.ComponentModel;
using System.Linq;
using AntlGrammar;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace AntlrInterpretor.Logic
{
    public class BuildAstVisitor : QLBaseVisitor<Reference<IAstNode>>
    {
        private readonly IAstFactory m_astFactory;

        private readonly IDomainItemLocator m_domainItemLocator;

        public BuildAstVisitor(
            IAstFactory astFactory,
            IDomainItemLocator domainItemLocator)
        {
            m_astFactory = astFactory;
            m_domainItemLocator = domainItemLocator;
        }

        public override Reference<IAstNode> VisitQuestionnaire(
            QLParser.QuestionnaireContext context)
        {
            var questionnaireName = context.IDENTIFIER().GetText();

            var statements = context.statement()
                .Select(Visit)
                .To<IStatementNode>(m_domainItemLocator);
                
            return m_astFactory.CreateQuestionnaire(
                context.GetText(),
                questionnaireName,
                statements);
        }

        public override Reference<IAstNode> VisitCalculatedQuestion(
            QLParser.CalculatedQuestionContext context)
        {
            var questionName = context.question().IDENTIFIER().GetText();
            var questionText = context.question().TEXT().GetText();
            var questionType = GetQuestionType(context.question());
            var calculation = Visit(context.calculatedValue())
                .To<ICalculationNode>(m_domainItemLocator);

            return m_astFactory.CreateCalculatedQuestion(
                context.GetText(),
                questionName, 
                questionText, 
                questionType,
                calculation);
        }

        public override Reference<IAstNode> VisitInputQuestion(
            QLParser.InputQuestionContext context)
        {
            var questionName = context.question().IDENTIFIER().GetText();
            var questionText = context
                .question()
                .TEXT()
                .GetText()
                .Replace("\"","");
            var questionType = GetQuestionType(context.question());

            return m_astFactory.CreateUserInputQuestion(
                context.GetText(),
                questionName,
                questionText,
                questionType);
        }

        public override Reference<IAstNode> VisitTypeCheckExpression(QLParser.TypeCheckExpressionContext context)
        {
            var leftExpression = m_astFactory.CreateUntypedVariableName(context.leftIdentifier.Text);
            var rightExpression = m_astFactory.CreateUntypedVariableName(context.rightIdentifier.Text);

            return CreateRelationalOperation(
                context.relationalOperator().chosenOperator.Type,
                context.GetText(),
                leftExpression,
                rightExpression,
                context.relationalOperator().chosenOperator.Text);
        }


        public override Reference<IAstNode> VisitIfElseStatement(
            QLParser.IfElseStatementContext context)
        {
            var questionName = context.conditionalStatement().expression().GetText();

            var predicate = Visit(context.conditionalStatement().expression())
                .To<IBooleanLogicNode>(m_domainItemLocator);
            
            var consequent = context
                .conditionalStatement()
                .consequentStatement()
                .statement()
                .Select(Visit)
                .To<IStatementNode>(m_domainItemLocator);
            
            var alternative = context
                .conditionalStatement()
                .alternativeStatement()
                ?.statement()
                .Select(Visit)
                .To<IStatementNode>(m_domainItemLocator);

            return m_astFactory.CreateConditional(
                questionName, 
                predicate, 
                consequent, 
                alternative); 
        }

        public override Reference<IAstNode> VisitMathComparison(QLParser.MathComparisonContext context)
        {
            var leftExpression = Visit(context.leftExpression);
            var rightExpression = Visit(context.rightExpression);

            return CreateRelationalOperation(
                context.@operator.chosenOperator.Type,
                context.GetText(),
                leftExpression,
                rightExpression,
                context.@operator.chosenOperator.Text);
        }

        public override Reference<IAstNode> VisitBooleanQuestionIdentifier(
            QLParser.BooleanQuestionIdentifierContext context)
        {
            return m_astFactory.CreateBooleanVariableName(context.GetText());
        }

        public override Reference<IAstNode> VisitBooleanExpressionGroup(QLParser.BooleanExpressionGroupContext context)
        {
            return Visit(context.booleanExpression());
        }

        public override Reference<IAstNode> VisitBooleanLiteral(QLParser.BooleanLiteralContext context)
        {
            return m_astFactory.CreateBooleanLiteral(context.GetText());
        }

        public override Reference<IAstNode> VisitAndOrStatement(QLParser.AndOrStatementContext context)
        {
            var leftExpression = Visit(context.leftExpression).To<IBooleanLogicNode>(m_domainItemLocator);
            var rightExpression = Visit(context.rightExpression).To<IBooleanLogicNode>(m_domainItemLocator);

            switch (context.booleanOperator().chosenOperator.Type)
            {
                case QLParser.AND:
                    return m_astFactory.CreateAndOperation(
                        context.GetText(), 
                        leftExpression, 
                        rightExpression);
                case QLParser.OR:
                    return m_astFactory.CreateOrOperation(
                        context.GetText(), 
                        leftExpression, 
                        rightExpression);
                default:
                    throw new QlParserException(
                        $@"Boolean Type '{context.booleanOperator().chosenOperator.Type}' handled in the parse tree but not by the AST",
                        null);
            }
        }

        private Type GetQuestionType(QLParser.QuestionContext question)
        {
            switch (question.questionType().chosenType.Type)
            {
                case QLParser.BOOLTYPE: return typeof(bool);
                case QLParser.STRINGTYPE: return typeof(string);
                case QLParser.INTTYPE: return typeof(int);
                case QLParser.DATETYPE: return typeof(DateTime);
                case QLParser.DECIMALTYPE: return typeof(decimal);
                default:
                    throw new QlParserException(
                        $@"QuestionType '{question.questionType().chosenType.Type}' handled in the parse tree but not by the AST",
                        null);
            }
        }
        
        public override Reference<IAstNode> VisitNumberVariableName(
            QLParser.NumberVariableNameContext context)
        {
            return m_astFactory.CreateNumberVariableName(context.GetText());
        }

        public override Reference<IAstNode> VisitCalculatedValue(
            QLParser.CalculatedValueContext context)
        {
            return Visit(context.mathExpression());
        }

        public override Reference<IAstNode> VisitAddSubtractExpression(QLParser.AddSubtractExpressionContext context)
        {
            var leftExpression = Visit(context.leftExpression)
                .To<ICalculationNode>(m_domainItemLocator);

            var rightExpression = Visit(context.rightExpression)
                .To<ICalculationNode>(m_domainItemLocator);

            var addSubtract = context.@operator.Type;
            switch (addSubtract)
            {
                case QLParser.ADD:
                    return m_astFactory.CreateAdditionOperation(
                        context.GetText(),
                        leftExpression,
                        rightExpression);
                case QLParser.MINUS:
                    return m_astFactory.CreateSubtractionOperation(
                        context.GetText(),
                        leftExpression,
                        rightExpression);
                default:
                    throw new QlParserException(
                        $"The provided operator '{context.@operator.Text}' is not handled within add or subtract in the statement {context.GetText()}.",
                        null);
            }
        }

        public override Reference<IAstNode> VisitMultiplyDivideExpression(QLParser.MultiplyDivideExpressionContext context)
        {
            var leftExpression = Visit(context.leftExpression)
                .To<ICalculationNode>(m_domainItemLocator);

            var rightExpression = Visit(context.rightExpression)
                .To<ICalculationNode>(m_domainItemLocator);


            var multiplyDivideOperator = context.@operator.Type;
            switch (multiplyDivideOperator)
            {
                case QLParser.MULTIPLY:
                    return m_astFactory.CreateMultiplicationOperation(
                        context.GetText(),
                        leftExpression,
                        rightExpression);
                case QLParser.DIVIDE:
                    return m_astFactory.CreateDivisionOperation(
                        context.GetText(),
                        leftExpression,
                        rightExpression);
                default:
                    throw new QlParserException(
                        $"The provided operator '{context.@operator.Text}' is not handled within multiply or divide in the statement {context.GetText()}.", 
                        null);
            }
        }

        public override Reference<IAstNode> VisitMathExpressionGroup(QLParser.MathExpressionGroupContext context)
        {
            return Visit(context.mathExpression());
        }
        //booleanComparison
        
        public override Reference<IAstNode> VisitNumberLiteral(
            QLParser.NumberLiteralContext context)
        {
            return m_astFactory.CreateNumber(context.GetText());
        }

        public override Reference<IAstNode> VisitNegationExpression(QLParser.NegationExpressionContext context)
        {
            var childExpression = Visit(context.booleanExpression())
                .To<IBooleanLogicNode>(m_domainItemLocator);

            return m_astFactory.CreateNegationOperation(
                context.GetText(),
                childExpression);
        }

        public override Reference<IAstNode> VisitDateComparison(QLParser.DateComparisonContext context)
        {
            var leftDate = context.leftDate.Type == QLParser.DATE
                ? m_astFactory.CreateDate(context.leftDate.Text)
                : m_astFactory.CreateDateVariableName(context.leftDate.Text);

            var rightDate = context.rightDate.Type == QLParser.DATE
                ? m_astFactory.CreateDate(context.rightDate.Text)
                : m_astFactory.CreateDateVariableName(context.rightDate.Text);

            return CreateRelationalOperation(
                context.@operator.chosenOperator.Type,
                context.GetText(),
                leftDate,
                rightDate, 
                context.@operator.chosenOperator.Text);
        }


        public override Reference<IAstNode> VisitTextComparison(QLParser.TextComparisonContext context)
        {
            var leftDate = context.leftText.Type == QLParser.TEXT
                ? m_astFactory.CreateText(context.leftText.Text)
                : m_astFactory.CreateTextVariableName(context.leftText.Text);

            var rightDate = context.rightText.Type == QLParser.TEXT
                ? m_astFactory.CreateText(context.rightText.Text)
                : m_astFactory.CreateTextVariableName(context.rightText.Text);

            return CreateEqualityOperation(
                context.@operator.chosenOperator.Type,
                context.GetText(),
                leftDate,
                rightDate,
                context.@operator.chosenOperator.Text);
        }


        public override Reference<IAstNode> VisitBooleanComparison(QLParser.BooleanComparisonContext context)
        {
            var leftExpression = Visit(context.leftExpression)
                .To<IAstNode>(m_domainItemLocator);

            var rightExpression = Visit(context.rightExpression)
                .To<IAstNode>(m_domainItemLocator);

            var relationalOperator = context.@operator.chosenOperator.Type;
            var operatorText = context.@operator.chosenOperator.Text;
            var definition = context.GetText();
            return CreateRelationalOperation(
                relationalOperator, 
                definition, 
                leftExpression, 
                rightExpression, 
                operatorText);
        }

        private Reference<IAstNode> CreateRelationalOperation(
            int relationalOperator,
            string definition,
            Reference<IAstNode> leftExpression,
            Reference<IAstNode> rightExpression,
            string operatorText)
        {
            switch (relationalOperator)
            {
                case QLParser.ISEQUAL:
                    return m_astFactory.CreateEqualityOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QLParser.ISNOTEQUAL:
                    return m_astFactory.CreateInequalityOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QLParser.ISGREATERTHAN:
                    return m_astFactory.CreateGreaterThanOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QLParser.ISGREATERTHANOREQUAL:
                    return m_astFactory.CreateGreaterOrEqualOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QLParser.ISLESSTHAN:
                    return m_astFactory.CreateLessThanOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QLParser.ISLESSTHANOREQUAL:
                    return m_astFactory.CreateLessOrEqualOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                default:
                    throw new QlParserException($"The relative operator '{operatorText}' is not recognized.", null);
            }
        }

        private Reference<IAstNode> CreateEqualityOperation(
            int relationalOperator,
            string definition,
            Reference<IAstNode> leftExpression,
            Reference<IAstNode> rightExpression,
            string operatorText)
        {
            switch (relationalOperator)
            {
                case QLParser.ISEQUAL:
                    return m_astFactory.CreateEqualityOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QLParser.ISNOTEQUAL:
                    return m_astFactory.CreateInequalityOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                default:
                    throw new QlParserException($"The equality operator '{operatorText}' is not recognized.", null);
            }
        }
    }
}