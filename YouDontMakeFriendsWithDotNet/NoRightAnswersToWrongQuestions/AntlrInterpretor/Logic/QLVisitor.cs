<<<<<<< HEAD
﻿using System;
using System.Linq;
using AntlGrammar;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.DomainObjects;

namespace AntlrInterpretor.Logic
{
    public class QlVisitor : QLBaseVisitor<IAstNode>
    {
        private readonly IQuestionnaireAst m_questionnaireAst;

        public QlVisitor()
        {
            m_questionnaireAst = new QuestionnaireAst();
        }

        public override IAstNode VisitQuestionnaire(QLParser.QuestionnaireContext context)
        {
            var formName = context.IDENTIFIER().GetText();
            context.question()
                .Select(x => Visit(x))
                .ToList();
            m_questionnaireAst.FormName = formName;
            return m_questionnaireAst;
        }
        
        public override IAstNode VisitQuestion(QLParser.QuestionContext context)
        {
            var name = context.IDENTIFIER().GetText();
            var text = context.QUESTIONTEXT().GetText();
            Type type ;
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

            var question = new QuestionAst(name, text.Replace("\"", ""), type);
            m_questionnaireAst.Statements.Add(question);
            return m_questionnaireAst;
        }
    }
=======
﻿using System.Linq;
using AntlGrammar;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.DomainObjects;

namespace AntlrInterpretor.Logic
{
    public class QlVisitor : QLBaseVisitor<IAstNode>
    {
        private readonly IQuestionnaireAst m_questionnaireAst;

        public QlVisitor()
        {
            m_questionnaireAst = new QuestionnaireAst();
        }

        public override IAstNode VisitQuestionnaire(QLParser.QuestionnaireContext context)
        {
            var formName = context.IDENT().GetText();
            context.question()
                .Select(x => Visit(x))
                .ToList();
            m_questionnaireAst.FormName = formName;
            return m_questionnaireAst;
        }

        public override IAstNode VisitQuestion(QLParser.QuestionContext context)
        {
            var name = context.IDENT().GetText();
            var text = context.STRING().GetText();
            var type = typeof(bool);
            var question = new QuestionAst(name, text.Replace("\"", ""), type);
            m_questionnaireAst.Questions.Add(question);
            return m_questionnaireAst;
        }
    }
>>>>>>> b4a9b6ed7a567bef7322e087eb0d3de8f04a3913
}