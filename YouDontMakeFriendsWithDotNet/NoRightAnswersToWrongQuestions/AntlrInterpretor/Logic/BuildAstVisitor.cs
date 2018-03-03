using System;
using System.Linq;
using AntlGrammar;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Boolean;
using QuestionaireDomain.Entities.API.AstNodes.Calculation;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.AstNodes.Relational;
using QuestionaireDomain.Entities.DomainObjects;

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
                questionName,
                questionText,
                questionType);
        }

        public override Reference<IAstNode> VisitIfElseStatement(
            QLParser.IfElseStatementContext context)
        {
            var questionName = context.conditionalStatement().booleanExpression().GetText();

            var predicate = Visit(context.conditionalStatement().booleanExpression())
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
                    return m_astFactory.CreateAndOperation(leftExpression, rightExpression);
                case QLParser.OR:
                    return m_astFactory.CreateOrOperation(leftExpression, rightExpression);
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
        
        public override Reference<IAstNode> VisitMathExpression(
            QLParser.MathExpressionContext context)
        {
            var calculationDefinition = context.GetText();
            return m_astFactory.CreateCalculation(calculationDefinition);
        }

        public override Reference<IAstNode> VisitNumberVariableName(
            QLParser.NumberVariableNameContext context)
        {
            return m_astFactory.CreateNumberVariableName(context.GetText());
        }

        public override Reference<IAstNode> VisitCalculatedValue(
            QLParser.CalculatedValueContext context)
        {
            return VisitMathExpression(context.mathExpression());
        }
        
        public override Reference<IAstNode> VisitNumberLiteral(
            QLParser.NumberLiteralContext context)
        {
            return m_astFactory.CreateNumber(context.GetText());
        }

        public override Reference<IAstNode> VisitNegationExpression(QLParser.NegationExpressionContext context)
        {

            var childExpression = Visit(context.booleanExpression())
                .To<IBooleanLogicNode>(m_domainItemLocator);

            return m_astFactory.CreateNegation(childExpression);
        }

        public override Reference<IAstNode> VisitBooleanComparison(QLParser.BooleanComparisonContext context)
        {
            var leftExpression = Visit(context.leftExpression)
                .To<IAstNode>(m_domainItemLocator);

            var rightExpression = Visit(context.rightExpression)
                .To<IAstNode>(m_domainItemLocator);

            var relationalOperator = context.@operator.chosenOperator.Type;
            switch (relationalOperator)
            {//to do: factory for each
                case QLParser.ISEQUAL:
                    return m_astFactory.CreateEquality(
                        leftExpression, 
                        rightExpression);
                case QLParser.ISNOTEQUAL:
                    return m_astFactory.CreateEquality(
                        leftExpression, 
                        rightExpression);
                case QLParser.ISGREATERTHAN:
                    return m_astFactory.CreateEquality(
                        leftExpression, 
                        rightExpression);
                case QLParser.ISGREATERTHANOREQUAL:
                    return m_astFactory.CreateEquality(
                        leftExpression, 
                        rightExpression);
                case QLParser.ISLESSTHAN:
                    return m_astFactory.CreateEquality(
                        leftExpression, 
                        rightExpression);
                case QLParser.ISLESSTHANOREQUAL:
                    return m_astFactory.CreateEquality(
                        leftExpression, 
                        rightExpression);
                default:
                    throw new QlParserException($"The relative operator '{context.@operator.chosenOperator.Text}' is not recognized.",null);
            }
        }
    }
}