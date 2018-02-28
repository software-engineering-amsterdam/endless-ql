using System;
using System.CodeDom.Compiler;
using System.Linq;
using AntlGrammar;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.DomainObjects;

namespace AntlrInterpretor.Logic
{
    public class BuildAstVisitor : QLBaseVisitor<IAstNode>
    {
        private readonly IAstFactory m_astFactory;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IQuestionnaireAst m_questionnaireAst;

        public BuildAstVisitor(
            IAstFactory astFactory,
            IDomainItemLocator domainItemLocator)
        {
            m_astFactory = astFactory;
            m_domainItemLocator = domainItemLocator;
            m_questionnaireAst = m_astFactory.CreateQuestionnaire();
        }

        public override IAstNode VisitQuestionnaire(QLParser.QuestionnaireContext context)
        {
            var formName = context.IDENTIFIER().GetText();
            context.statement()
                .Select(x => Visit(x))
                .ToList();
            m_questionnaireAst.FormName = formName;
            return m_questionnaireAst;
        }
        
        public override IAstNode VisitMathexpression(QLParser.MathexpressionContext context)
        {
            var calculationDefinition = context.GetText();
            var calculation = m_astFactory.CreateCalculation(calculationDefinition);

            return calculation;
        }

        public override IAstNode VisitNumberId(QLParser.NumberIdContext context)
        {
            return base.VisitNumberId(context);
        }


        public override IAstNode VisitCalculatedValue(QLParser.CalculatedValueContext context)
        {
            return VisitMathexpression(context.mathexpression());
        }

        public override IAstNode VisitConditional(QLParser.ConditionalContext context)
        {
            var questionName = context.condition().GetText();
            var conditional = m_astFactory.CreateConditional(questionName);

            m_questionnaireAst.ChildNodes.Add(conditional);
            Visit(context.condition());
            context.statement()
                .Select(x => Visit(x))
                .ToList();

            return m_questionnaireAst;
        }
        
        public override IAstNode VisitQuestion(QLParser.QuestionContext context)
        {
            var name = context.IDENTIFIER().GetText();

            //ToDo: move this to the static analyzer
            var questionExists = m_domainItemLocator.GetAll<IQuestionAst>().Any(x => x.Name == name);
            if (questionExists)
            {
                var message = $@"The question with the id '{name}' exists more than once";
                throw new QlParserException(message, null) { ParseErrorDetails = message };
            }
            

            var text = context.TEXT().GetText();
            Type type;
            switch (context.questiontype().qtype.Type)
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
                        $@"Type '{context.questiontype().qtype.Type}' handled in the parse tree but not by the AST",
                        null);
            }


            var question = m_astFactory.CreateQuestion(name, text.Replace("\"", ""), type);

            return m_questionnaireAst;
        }
    }
}