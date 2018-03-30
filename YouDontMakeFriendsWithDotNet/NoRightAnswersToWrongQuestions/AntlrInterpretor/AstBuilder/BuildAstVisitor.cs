using System;
using System.Linq;
using AntlGrammar;
using QuestionnaireDomain.Entities.Ast.Nodes.Boolean.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Calculation.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Common.Interfaces;
using QuestionnaireDomain.Entities.Ast.Nodes.Questionnaire.Interfaces;
using QuestionnaireDomain.Entities.Ast.Tools.Interfaces;
using QuestionnaireDomain.Entities.Domain;
using QuestionnaireDomain.Entities.Domain.Interfaces;

namespace AntlrInterpretor.AstBuilder
{
    public class BuildAstVisitor : QlBaseVisitor<DomainId<IAstNode>>
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

        public override DomainId<IAstNode> VisitQuestionnaire(
            QlParser.QuestionnaireContext context)
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

        public override DomainId<IAstNode> VisitCalculatedQuestion(
            QlParser.CalculatedQuestionContext context)
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

        public override DomainId<IAstNode> VisitInputQuestion(
            QlParser.InputQuestionContext context)
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

        public override DomainId<IAstNode> VisitTypeCheckExpression(QlParser.TypeCheckExpressionContext context)
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
        
        public override DomainId<IAstNode> VisitIfElseStatement(
            QlParser.IfElseStatementContext context)
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

        public override DomainId<IAstNode> VisitMathComparison(QlParser.MathComparisonContext context)
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

        public override DomainId<IAstNode> VisitBooleanQuestionIdentifier(
            QlParser.BooleanQuestionIdentifierContext context)
        {
            return m_astFactory.CreateBooleanVariableName(context.GetText());
        }

        public override DomainId<IAstNode> VisitBooleanExpressionGroup(QlParser.BooleanExpressionGroupContext context)
        {
            return Visit(context.booleanExpression());
        }

        public override DomainId<IAstNode> VisitBooleanLiteral(QlParser.BooleanLiteralContext context)
        {
            return m_astFactory.CreateBooleanLiteral(context.GetText());
        }

        public override DomainId<IAstNode> VisitAndOrStatement(QlParser.AndOrStatementContext context)
        {
            var leftExpression = Visit(context.leftExpression).To<IBooleanLogicNode>(m_domainItemLocator);
            var rightExpression = Visit(context.rightExpression).To<IBooleanLogicNode>(m_domainItemLocator);

            switch (context.booleanOperator().chosenOperator.Type)
            {
                case QlParser.AND:
                    return m_astFactory.CreateAndOperation(
                        context.GetText(), 
                        leftExpression, 
                        rightExpression);
                case QlParser.OR:
                    return m_astFactory.CreateOrOperation(
                        context.GetText(), 
                        leftExpression, 
                        rightExpression);
                default:
                    throw new ParserException(
                        $@"Boolean Type '{context.booleanOperator().chosenOperator.Type}' handled in the parse tree but not by the AST",
                        null);
            }
        }

        private Type GetQuestionType(QlParser.QuestionContext question)
        {
            switch (question.questionType().chosenType.Type)
            {
                case QlParser.BOOLTYPE: return typeof(bool);
                case QlParser.STRINGTYPE: return typeof(string);
                case QlParser.INTTYPE: return typeof(int);
                case QlParser.DATETYPE: return typeof(DateTime);
                case QlParser.DECIMALTYPE: return typeof(decimal);
                default:
                    throw new ParserException(
                        $@"QuestionType '{question.questionType().chosenType.Type}' handled in the parse tree but not by the AST",
                        null);
            }
        }
        
        public override DomainId<IAstNode> VisitNumberVariableName(
            QlParser.NumberVariableNameContext context)
        {
            return m_astFactory.CreateNumberVariableName(context.GetText());
        }

        public override DomainId<IAstNode> VisitCalculatedValue(
            QlParser.CalculatedValueContext context)
        {
            return Visit(context.mathExpression());
        }

        public override DomainId<IAstNode> VisitAddSubtractExpression(QlParser.AddSubtractExpressionContext context)
        {
            var leftExpression = Visit(context.leftExpression)
                .To<ICalculationNode>(m_domainItemLocator);

            var rightExpression = Visit(context.rightExpression)
                .To<ICalculationNode>(m_domainItemLocator);

            var addSubtract = context.@operator.Type;
            switch (addSubtract)
            {
                case QlParser.ADD:
                    return m_astFactory.CreateAdditionOperation(
                        context.GetText(),
                        leftExpression,
                        rightExpression);
                case QlParser.MINUS:
                    return m_astFactory.CreateSubtractionOperation(
                        context.GetText(),
                        leftExpression,
                        rightExpression);
                default:
                    throw new ParserException(
                        $"The provided operator '{context.@operator.Text}' is not handled within add or subtract in the statement {context.GetText()}.",
                        null);
            }
        }

        public override DomainId<IAstNode> VisitMultiplyDivideExpression(QlParser.MultiplyDivideExpressionContext context)
        {
            var leftExpression = Visit(context.leftExpression)
                .To<ICalculationNode>(m_domainItemLocator);

            var rightExpression = Visit(context.rightExpression)
                .To<ICalculationNode>(m_domainItemLocator);

            var multiplyDivideOperator = context.@operator.Type;
            switch (multiplyDivideOperator)
            {
                case QlParser.MULTIPLY:
                    return m_astFactory.CreateMultiplicationOperation(
                        context.GetText(),
                        leftExpression,
                        rightExpression);
                case QlParser.DIVIDE:
                    return m_astFactory.CreateDivisionOperation(
                        context.GetText(),
                        leftExpression,
                        rightExpression);
                default:
                    throw new ParserException(
                        $"The provided operator '{context.@operator.Text}' is not handled within multiply or divide in the statement {context.GetText()}.", 
                        null);
            }
        }

        public override DomainId<IAstNode> VisitMathExpressionGroup(QlParser.MathExpressionGroupContext context)
        {
            return Visit(context.mathExpression());
        }

        public override DomainId<IAstNode> VisitNumberLiteral(
            QlParser.NumberLiteralContext context)
        {
            return m_astFactory.CreateNumber(context.GetText());
        }

        public override DomainId<IAstNode> VisitNegationExpression(QlParser.NegationExpressionContext context)
        {
            var childExpression = Visit(context.booleanExpression())
                .To<IBooleanLogicNode>(m_domainItemLocator);

            return m_astFactory.CreateNegationOperation(
                context.GetText(),
                childExpression);
        }

        public override DomainId<IAstNode> VisitDateComparison(QlParser.DateComparisonContext context)
        {
            var leftDate = context.leftDate.Type == QlParser.DATE
                ? m_astFactory.CreateDate(context.leftDate.Text)
                : m_astFactory.CreateDateVariableName(context.leftDate.Text);

            var rightDate = context.rightDate.Type == QlParser.DATE
                ? m_astFactory.CreateDate(context.rightDate.Text)
                : m_astFactory.CreateDateVariableName(context.rightDate.Text);

            return CreateRelationalOperation(
                context.@operator.chosenOperator.Type,
                context.GetText(),
                leftDate,
                rightDate, 
                context.@operator.chosenOperator.Text);
        }

        public override DomainId<IAstNode> VisitTextComparison(QlParser.TextComparisonContext context)
        {
            var leftDate = context.leftText.Type == QlParser.TEXT
                ? m_astFactory.CreateText(context.leftText.Text)
                : m_astFactory.CreateTextVariableName(context.leftText.Text);

            var rightDate = context.rightText.Type == QlParser.TEXT
                ? m_astFactory.CreateText(context.rightText.Text)
                : m_astFactory.CreateTextVariableName(context.rightText.Text);

            return CreateEqualityOperation(
                context.@operator.chosenOperator.Type,
                context.GetText(),
                leftDate,
                rightDate,
                context.@operator.chosenOperator.Text);
        }

        public override DomainId<IAstNode> VisitBooleanComparison(QlParser.BooleanComparisonContext context)
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

        private DomainId<IAstNode> CreateRelationalOperation(
            int relationalOperator,
            string definition,
            DomainId<IAstNode> leftExpression,
            DomainId<IAstNode> rightExpression,
            string operatorText)
        {
            switch (relationalOperator)
            {
                case QlParser.ISEQUAL:
                    return m_astFactory.CreateEqualityOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QlParser.ISNOTEQUAL:
                    return m_astFactory.CreateInequalityOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QlParser.ISGREATERTHAN:
                    return m_astFactory.CreateGreaterThanOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QlParser.ISGREATERTHANOREQUAL:
                    return m_astFactory.CreateGreaterOrEqualOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QlParser.ISLESSTHAN:
                    return m_astFactory.CreateLessThanOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QlParser.ISLESSTHANOREQUAL:
                    return m_astFactory.CreateLessOrEqualOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                default:
                    throw new ParserException($"The relative operator '{operatorText}' is not recognized.", null);
            }
        }

        private DomainId<IAstNode> CreateEqualityOperation(
            int relationalOperator,
            string definition,
            DomainId<IAstNode> leftExpression,
            DomainId<IAstNode> rightExpression,
            string operatorText)
        {
            switch (relationalOperator)
            {
                case QlParser.ISEQUAL:
                    return m_astFactory.CreateEqualityOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                case QlParser.ISNOTEQUAL:
                    return m_astFactory.CreateInequalityOperation(
                        definition,
                        leftExpression,
                        rightExpression);
                default:
                    throw new ParserException($"The equality operator '{operatorText}' is not recognized.", null);
            }
        }
    }
}