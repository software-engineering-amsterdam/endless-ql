using System;
using System.CodeDom.Compiler;
using System.Collections.Generic;
using System.ComponentModel.Design;
using System.Linq;
using AntlGrammar;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
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

        public override Reference<IAstNode> VisitNumberLiteral(QLParser.NumberLiteralContext context)
        {
            return m_astFactory.CreateNumber(context.GetText());
        }
        
        public override Reference<IAstNode> VisitQuestionnaire(QLParser.QuestionnaireContext context)
        {
            var questionnaireName = context.IDENTIFIER().GetText();

            var statements = context.statement()
                .Select(x => Visit(x))
                .To<IStatementNode>(m_domainItemLocator);
                
            return m_astFactory.CreateQuestionnaire(
                questionnaireName,
                statements);
        }
        
        public override Reference<IAstNode> VisitMathExpression(QLParser.MathExpressionContext context)
        {
            var calculationDefinition = context.GetText();
            return m_astFactory.CreateCalculation(calculationDefinition);
        }

        public override Reference<IAstNode> VisitNumberVariableName(QLParser.NumberVariableNameContext context)
        {
            return m_astFactory.CreateNumberVariableName(context.GetText());
        }

        public override Reference<IAstNode> VisitCalculatedValue(QLParser.CalculatedValueContext context)
        {
            return VisitMathExpression(context.mathExpression());
        }

        public override Reference<IAstNode> VisitConditionalStatement(QLParser.ConditionalStatementContext context)
        {
            var questionName = context.booleanExpression().GetText();
            var conditional = m_astFactory
                .CreateConditional(questionName);
            
            Visit(context.booleanExpression());
            context.statement()
                .Select(Visit)
                .ToList();

            return conditional;
        }
        
        public override Reference<IAstNode> VisitQuestion(QLParser.QuestionContext context)
        {
            var name = context.IDENTIFIER().GetText();

            //ToDo: move this to the static analyzer
            //var questionExists = m_domainItemLocator.GetAll<IQuestionAst>().Any(x => x.QuestionId == name);
            //if (questionExists)
            //{
            //    var message = $@"The question with the id '{name}' exists more than once";
            //    throw new QlParserException(message, null) { ParseErrorDetails = message };
            //}
            

            var text = context.TEXT().GetText();
            Type type;
            switch (context.questionType().chosenType.Type)
            {
                case QLParser.BOOLTYPE:
                    type = typeof(bool);
                    break;
                case QLParser.STRINGTYPE:
                    type = typeof(string);
                    break;
                case QLParser.INTTYPE:
                    type = typeof(int);
                    break;
                case QLParser.DATETYPE:
                    type = typeof(DateTime);
                    break;
                case QLParser.DECIMALTYPE:
                    type = typeof(decimal);
                    break;
                default:
                    throw new QlParserException(
                        $@"QuestionType '{context.questionType().chosenType.Type}' handled in the parse tree but not by the AST",
                        null);
            }

            return m_astFactory.CreateQuestion(
                name, 
                text.Replace("\"", ""), 
                type);
        }
    }

    public static class AstExtensions
    {
        public static IEnumerable<Reference<T>> To<T>(
            this IEnumerable<Reference<IAstNode>> nodes,
            IDomainItemLocator domainItemLocator) where T : IAstNode
        {
            return nodes.Select(x => x.To<T>(domainItemLocator)).ToList(); 
        }

        public static Reference<T> To<T>(
            this Reference<IAstNode> node, 
            IDomainItemLocator domainItemLocator) where T : IAstNode
        {
            var domainItem = domainItemLocator.Get<T>(node.Id);
            return new Reference<T> {Id = domainItem.Id};
        }
    }
}